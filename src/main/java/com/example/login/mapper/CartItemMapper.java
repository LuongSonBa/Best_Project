package com.example.login.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;
import com.example.login.entity.CartItem;

@Component
public class CartItemMapper {

    // Hàm 1: Map từng món lẻ (Giữ nguyên)
    public CartItemResponseDto toResponseDto(CartItem cartItem) {
        if (cartItem == null) return null;
        CartItemResponseDto dto = new CartItemResponseDto();

        dto.setCartItemId(cartItem.getId());
        dto.setComputerName(cartItem.getComputer().getName());
        dto.setComputerId(cartItem.getComputer().getId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setPrice(cartItem.getComputer().getPrice());
        dto.setIsSelected(cartItem.getIsSelected());

        if (cartItem.getComputer().getPrice() != null && cartItem.getQuantity() != null) {
            BigDecimal subtotal = cartItem.getComputer().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            dto.setSubtotal(subtotal);
        }

        return dto;
    }

    // Hàm 2: Nhận List từ Service và xử lý tổng thể
    public CartResponseDto toCartResponseDto(List<CartItem> cartItems) {
        if (cartItems == null) return null;

        CartResponseDto response = new CartResponseDto();
        
        // 1. Lấy Cart ID từ item đầu tiên (vì tất cả items trong list đều cùng 1 giỏ hàng)
        if (!cartItems.isEmpty()) {
            response.setCartId(cartItems.get(0).getCart().getId());
        }

        // 2. Map list sang DTOs
        List<CartItemResponseDto> dtos = cartItems.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        response.setItems(dtos);

        // 3. Tính tổng tiền từ những món được tick
        BigDecimal total = cartItems.stream()
        	    .filter(item -> Boolean.TRUE.equals(item.getIsSelected()))
        	    .map(item -> item.getComputer().getPrice()
        	        .multiply(BigDecimal.valueOf(item.getQuantity())))
        	    .reduce(BigDecimal.ZERO, BigDecimal::add);

        	response.setTotalPrice(total);
        
        return response;
    }
}