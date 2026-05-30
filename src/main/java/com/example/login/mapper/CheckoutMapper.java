package com.example.login.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CheckoutItemDto;
@Component
public class CheckoutMapper {
    public CheckoutItemDto toCheckoutItemDto(CartItemResponseDto item) {
        if (item == null) return null; // Thêm check null cho chắc

        CheckoutItemDto dto = new CheckoutItemDto();
        dto.setCartItemId(item.getCartItemId());
        dto.setComputerName(item.getComputerName());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());

        if (item.getPrice() != null && item.getQuantity() != null) {
            dto.setSubtotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        } else {
            dto.setSubtotal(BigDecimal.ZERO);
        }
        
        return dto;
    }
}