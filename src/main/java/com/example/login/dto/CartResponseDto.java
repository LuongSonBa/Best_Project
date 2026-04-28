package com.example.login.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartResponseDto {
	private List<CartItemResponseDto> items; // Danh sách các món hàng
    private BigDecimal totalPrice;           // Tính năng: Tổng tiền của những món được chọn
	public List<CartItemResponseDto> getItems() {
		return items;
	}
	public void setItems(List<CartItemResponseDto> items) {
		this.items = items;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
    
}
