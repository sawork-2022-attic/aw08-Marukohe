package com.micropos.counter.rest;

import com.micropos.common.api.CounterApi;
import com.micropos.common.dto.CartDto;
import com.micropos.counter.service.CounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CounterController implements CounterApi {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @Override
    public ResponseEntity<Double> checkout(CartDto cart) {
        return new ResponseEntity<>(counterService.getTotalPrice(cart), HttpStatus.OK);
    }
}
