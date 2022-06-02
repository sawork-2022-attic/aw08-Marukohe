package com.micropos.carts.repository;

import com.micropos.common.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class ProductRepositoryImpl implements ProductRepository {
    private final RestTemplate restTemplate;

    public ProductRepositoryImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Product getProduct(String productId) {
        String url = String.format("http://localhost:8082/api/products/%s", productId);
        return restTemplate.getForEntity(url, Product.class).getBody();
    }
}
