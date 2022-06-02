package com.micropos.delivery;

import com.micropos.common.model.Delivery;
import com.micropos.common.model.Order;
import com.micropos.delivery.service.DeliveryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
public class DeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    public Consumer<Order> createDelivery(DeliveryService deliveryService) {
        return (order) -> {
            Delivery delivery = Delivery.builder()
                    .id(UUID.randomUUID().toString())
                    .orderId(order.getId())
                    .build();
            deliveryService.createDelivery(delivery);
        };
    }
}
