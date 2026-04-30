package com.example.login.service;

import java.util.List;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;

public interface CartService {
	CartItemResponseDto addToCart (
		Long userId,
		CartItemRequestDto request
		);
	CartResponseDto getCartByUserId(Long userId);
	
	CartResponseDto updateFullCart(Long userId, List<CartItemRequestDto> requests);
	
	public void deleteItem(Long itemId);
	}
	
