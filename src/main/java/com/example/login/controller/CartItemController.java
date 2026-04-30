package com.example.login.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;
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
    @PutMapping("/update-all")
    public ResponseEntity<CartResponseDto> updateAll(@RequestParam Long userId, @RequestBody List<CartItemRequestDto> requests) {
        return ResponseEntity.ok(cartService.updateFullCart(userId, requests));
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        cartService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }
}