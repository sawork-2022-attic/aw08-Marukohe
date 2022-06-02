package com.micropos.carts.service;

import com.micropos.common.model.Cart;
import com.micropos.common.model.Order;

public interface CartService {
    Cart getCart();

    Cart addProduct(String productId);

    Cart removeProduct(String productId);

    Order checkoutCart();
}
