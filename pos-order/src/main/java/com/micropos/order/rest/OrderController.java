package com.micropos.order.rest;

import com.micropos.common.api.OrdersApi;
import com.micropos.common.dto.OrderDto;
import com.micropos.common.model.Order;
import com.micropos.order.mapper.OrderMapper;
import com.micropos.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController implements OrdersApi {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderDto> createOrder() {
        Order order = orderService.createOrder();
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(orderMapper.toOrderDto(order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> listOrders() {
        List<OrderDto> orders = new ArrayList<>(orderMapper.toOrdersDto(orderService.getOrders()));
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
