package com.micropos.order.service;

import com.micropos.common.model.Order;
import com.micropos.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    private final StreamBridge streamBridge;

    public OrderServiceImpl(OrderRepository orderRepository, StreamBridge streamBridge) {
        this.orderRepository = orderRepository;
        this.streamBridge = streamBridge;
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orderRepository.findAll());
    }

    @Override
    public Order createOrder() {
        String url = "http://POS-CARTS/api/carts/checkout";
        Order order = restTemplate.postForEntity(url, null, Order.class).getBody();
        if (order != null) {
            orderRepository.save(order);
            streamBridge.send("createOrder-out-0", order);
        }
        return order;
    }
}
