package com.micropos.delivery.rest;

import com.micropos.common.api.DeliveriesApi;
import com.micropos.common.dto.DeliveryDto;
import com.micropos.common.model.Delivery;
import com.micropos.delivery.mapper.DeliveryMapper;
import com.micropos.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class DeliveryController implements DeliveriesApi {
    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public ResponseEntity<DeliveryDto> getDeliveryByOrderId(String orderId) {
        Delivery delivery = deliveryService.findDeliveryByOrderId(orderId);
        if (delivery == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deliveryMapper.toDeliveryDto(delivery), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {
        List<DeliveryDto> deliveries = new ArrayList<>(deliveryMapper.toDeliveriesDto(deliveryService.getDeliveries()));
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }
}
