package com.example.login.controller;
import java.security.Principal;

import org.springframework.batch.core.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.login.service.CartService;

@Controller
    @RequestMapping("/cart")
    public class CartPageController {

        private final Job expireJob;
        private final CartService cartService;
        public CartPageController(CartService cartService, Job expireJob) { this.cartService = cartService; this.expireJob = expireJob; }

        @GetMapping
        public String showCartPage(Model model, Principal principal) {
        	String username = principal.getName();
            model.addAttribute("cart", cartService.getCartByUsername(username));
            return "cart-item";
        }
    }
