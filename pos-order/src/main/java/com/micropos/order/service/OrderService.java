package com.micropos.order.service;

import com.micropos.common.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order createOrder();
}
