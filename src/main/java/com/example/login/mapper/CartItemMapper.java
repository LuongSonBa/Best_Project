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

    public CartItemResponseDto toResponseDto(CartItem item) {
        CartItemResponseDto dto = new CartItemResponseDto();

        dto.setCartItemId(item.getId());
        dto.setComputerId(item.getComputerId());        
        dto.setComputerName(item.getComputerName());    
        dto.setPrice(item.getComputerPrice());          
        dto.setQuantity(item.getQuantity());
        dto.setIsSelected(item.getIsSelected());

        BigDecimal subtotal = item.getComputerPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));
        dto.setSubtotal(subtotal);

        return dto;
    }

 
    public CartResponseDto toCartResponseDto(List<CartItem> items) {
        CartResponseDto response = new CartResponseDto();


        if (!items.isEmpty()) {
            response.setCartId(items.get(0).getCartId());
        }

        List<CartItemResponseDto> dtos = items.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        response.setItems(dtos);

        BigDecimal total = dtos.stream()
                .filter(i -> Boolean.TRUE.equals(i.getIsSelected()))
                .map(CartItemResponseDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        response.setTotalPrice(total);

        return response;
    }
}
