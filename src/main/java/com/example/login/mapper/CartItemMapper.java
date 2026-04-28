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
	// --- HÀM 2: Phục vụ Trang Cart Page (Viết thêm mới hoàn toàn) ---
    // Hàm này sẽ gom toàn bộ danh sách CartItem và tính Tổng Tiền
    public CartResponseDto toCartResponseDto(List<CartItem> entities) {
        CartResponseDto response = new CartResponseDto();
        
        // Dùng chính hàm toResponseDto (Hàm 1) để map từng item trong List
        List<CartItemResponseDto> dtos = entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        
        response.setItems(dtos);

        // Tính tổng tiền cho cả giỏ hàng (Chỉ cộng những món được chọn)
        BigDecimal totalPrice = dtos.stream()
                .filter(item -> item.getIsSelected() != null && item.getIsSelected())
                .map(CartItemResponseDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        response.setTotalPrice(totalPrice);
        
        return response;
    }

}
