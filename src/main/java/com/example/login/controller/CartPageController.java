package com.example.login.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.login.dto.CartResponseDto;
import com.example.login.service.CartService;

import org.springframework.ui.Model;

public class CartPageController {
	private final CartService cartService;

    public CartPageController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String showCartPage(Model model) {
        Long userId = 1L; 
        
        CartResponseDto cartResponse = cartService.getCartByUserId(userId);
        
        model.addAttribute("cart", cartResponse);
        
        return "cart_item";
    }
}
