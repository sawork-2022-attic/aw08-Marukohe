package com.micropos.carts.repository;

import com.micropos.common.model.Product;

public interface ProductRepository {
    public Product getProduct(String productId);
}
