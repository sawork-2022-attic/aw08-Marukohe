package com.micropos.delivery.service;

import com.micropos.common.model.Delivery;

import java.util.List;

public interface DeliveryService {
    void createDelivery(Delivery delivery);

    Delivery findDeliveryByOrderId(String orderId);

    List<Delivery> getDeliveries();
}
