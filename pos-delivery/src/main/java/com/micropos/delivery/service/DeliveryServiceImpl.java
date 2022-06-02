package com.micropos.delivery.service;

import com.micropos.common.model.Delivery;
import com.micropos.delivery.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void createDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public Delivery findDeliveryByOrderId(String orderId) {
        for (Delivery delivery : deliveryRepository.findAll()) {
            if (delivery.getOrderId().equals(orderId)) {
                return delivery;
            }
        }
        return null;
    }

    @Override
    public List<Delivery> getDeliveries() {
        return new ArrayList<>(deliveryRepository.findAll());
    }
}
