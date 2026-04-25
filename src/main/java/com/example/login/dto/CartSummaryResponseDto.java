package com.example.login.dto;

public class CartSummaryResponseDto {
    private Integer totalSelectedItems;
    private Integer totalPrice;
	public Integer getTotalSelectedItems() {
		return totalSelectedItems;
	}
	public void setTotalSelectedItems(Integer totalSelectedItems) {
		this.totalSelectedItems = totalSelectedItems;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
