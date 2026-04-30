package com.example.login.dto;

import java.math.BigDecimal;

public class CartItemResponseDto {

    private Long cartItemId;
    private Long computerId;   
    private String computerName;
    private String computerImage; 
    private Integer quantity;
    private BigDecimal price;
    private Boolean isSelected;
    private BigDecimal subtotal; 

    // --- GETTER & SETTER ---

    public Long getCartItemId() { return cartItemId; }
    public void setCartItemId(Long cartItemId) { this.cartItemId = cartItemId; }

    public Long getComputerId() { return computerId; }
    public void setComputerId(Long computerId) { this.computerId = computerId; }

    public String getComputerName() { return computerName; }
    public void setComputerName(String computerName) { this.computerName = computerName; }

    // SỬA LẠI ĐOẠN NÀY
    public String getComputerImage() { return computerImage; }
    public void setComputerImage(String computerImage) { this.computerImage = computerImage; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Boolean getIsSelected() { return isSelected; }
    public void setIsSelected(Boolean isSelected) { this.isSelected = isSelected; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}