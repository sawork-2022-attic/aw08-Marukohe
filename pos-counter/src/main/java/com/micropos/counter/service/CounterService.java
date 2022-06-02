package com.micropos.counter.service;

import com.micropos.common.dto.CartDto;
import com.micropos.common.dto.ItemDto;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    public double getTotalPrice(CartDto cart) {
        double total = 0;
        for (ItemDto item : cart.getItems()) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }
}
