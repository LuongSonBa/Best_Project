package com.example.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private final CartService cartService;

    public CartItemController(
            CartService cartService
    ) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItemResponseDto> addToCart(
            @RequestParam Long userId,
            @RequestBody CartItemRequestDto request
    ) {
        return ResponseEntity.ok(
                cartService.addToCart(userId, request)
        );
    }
}