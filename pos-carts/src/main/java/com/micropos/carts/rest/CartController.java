package com.micropos.carts.rest;

import com.micropos.common.api.CartsApi;
import com.micropos.common.dto.CartDto;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.common.dto.OrderDto;
import com.micropos.common.model.Cart;
import com.micropos.carts.service.CartService;
import com.micropos.common.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CartController implements CartsApi {

    private final CartMapper cartMapper;
    private final CartService cartService;

    public CartController(CartMapper cartMapper, CartService cartService) {
        this.cartMapper = cartMapper;
        this.cartService = cartService;
    }

    @Override
    public ResponseEntity<CartDto> addProductById(String productId) {
        Cart cart = cartService.addProduct(productId);
        return new ResponseEntity<>(cartMapper.toCartDto(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> getCart() {
        Cart cart = cartService.getCart();
        return new ResponseEntity<>(cartMapper.toCartDto(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> removeProduct(String productId) {
        Cart cart = cartService.removeProduct(productId);
        return new ResponseEntity<>(cartMapper.toCartDto(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> checkoutCart() {
        Order order = cartService.checkoutCart();
        return new ResponseEntity<>(cartMapper.toOrderDto(order), HttpStatus.OK);
    }
}
