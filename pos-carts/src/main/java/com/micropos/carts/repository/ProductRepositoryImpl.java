package com.micropos.carts.repository;

import com.micropos.common.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public ProductRepositoryImpl() {
    }

    @Override
    public Product getProduct(String productId) {
        String url = String.format("http://POS-PRODUCTS/api/products/%s", productId);
        return restTemplate.getForEntity(url, Product.class).getBody();
    }
}
