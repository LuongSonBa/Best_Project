package com.example.login.service;

import java.util.List;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;

public interface CartService {
	CartItemResponseDto addToCart (
		String username,
		CartItemRequestDto request
		);
	CartResponseDto getCartByUserName(String username);
	
	CartResponseDto updateFullCart(String username, List<CartItemRequestDto> requests);
	
	public void deleteItem(Long itemId,String username);
	}
	
