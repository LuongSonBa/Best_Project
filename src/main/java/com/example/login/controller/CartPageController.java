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
	private final CartService cartService;

	public CartPageController(CartService cartService) {
		this.cartService = cartService;

	}

	@GetMapping
    public String showCartPage(Model model, Principal principal) {
		String userName = principal.getName();
		model.addAttribute("cart", cartService.getCartByUserName(userName));
		return "cart-item";
	}
}
