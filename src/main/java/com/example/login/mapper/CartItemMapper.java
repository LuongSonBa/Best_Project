package com.example.login.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.login.dto.CartItemResponseDto;
import com.example.login.entity.CartItem;

@Component
public class CartItemMapper {
	public CartItemResponseDto toResponseDto(CartItem cartItem) {
		CartItemResponseDto dto = new CartItemResponseDto();

		dto.setCartItemId(cartItem.getId());
		dto.setProductName(cartItem.getProduct().getName());
		dto.setQuantity(cartItem.getQuantity());
		dto.setPrice(cartItem.getProduct().getPrice());
		dto.setIsSelected(cartItem.getIsSelected());

		BigDecimal subtotal = cartItem.getProduct().getPrice()
								.multiply(BigDecimal.valueOf(cartItem.getQuantity()));

		dto.setSubtotal(subtotal);

		return dto;
	}

}
