package com.example.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.login.service.CartService;

    @Controller
    @RequestMapping("/cart")
    public class CartPageController {
        private final CartService cartService;
        public CartPageController(CartService cartService) { this.cartService = cartService; }

        @GetMapping
        public String showCartPage(Model model) {
            Long userId = 1L; // Thay bằng ID từ User thực tế
            model.addAttribute("cart", cartService.getCartByUserId(userId));
            return "cart-item";
        }
    }
