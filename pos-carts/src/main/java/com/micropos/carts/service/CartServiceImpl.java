package com.micropos.carts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.common.model.Cart;
import com.micropos.common.model.Item;
import com.micropos.common.model.Order;
import com.micropos.common.model.Product;
import com.micropos.carts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;

@Service
class CartServiceImpl implements CartService {

    private final ProductRepository db;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    private Cart cart;

    public CartServiceImpl(ProductRepository db) {
        this.db = db;
    }

    @Override
    public Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    @Override
    public Cart addProduct(String productId) {
        Cart cart = getCart();
        Product product = db.getProduct(productId);
        if (product == null) {
            return cart;
        }
        for (Item item : cart.getItems()) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return cart;
            }
        }
        cart.addItem(new Item(product, 1));
        return cart;
    }

    @Override
    public Cart removeProduct(String productId) {
        Cart cart = getCart();
        Product product = db.getProduct(productId);
        if (product == null) {
            return cart;
        }
        for (Item item : cart.getItems()) {
            if (item.getProduct().equals(product)) {
                cart.removeItem(item);
                return cart;
            }
        }
        return cart;
    }

    @Override
    public Order checkoutCart() {
        String counterUrl = "http://POS-COUNTER/api/counter/checkout";
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request;
        try {
            request = new HttpEntity<>(mapper.writeValueAsString(getCart()), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Double total = restTemplate.postForEntity(counterUrl, request, Double.class).getBody();
        if (total == null) {
            return null;
        }

        Order order = new Order();

        order.setId(UUID.randomUUID().toString());
        order.getItems().addAll(getCart().getItems());
        order.setCounter(total);

        cart = new Cart();  // empty cart

        return order;
    }
}
