package com.example.login.dto;

public class CartItemRequestDto {

	private Long computerId;
    private Integer quantity;
    private Boolean isSelected;
    private Long cartItemId;
    
	public Long getComputerId() {
		return computerId;
	}
	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}
	public Long getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(Long cartItemId) {
		this.cartItemId = cartItemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

}