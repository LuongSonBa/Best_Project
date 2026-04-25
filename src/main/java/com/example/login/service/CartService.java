package com.example.login.service;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;

public interface CartService {
	CartItemResponseDto addToCart (
		Long userId,
		CartItemRequestDto request
		);
	}
	
