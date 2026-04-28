package com.example.login.dto;

import java.math.BigDecimal;

public class CartItemResponseDto {

    private Long cartItemId;
    private Long productId;
    private String productName;
    private String productImage; // 1. Bổ sung để hiện ảnh
    private Integer quantity;
    private BigDecimal price;
    private Boolean isSelected;
    private BigDecimal subtotal; // 2. Đổi tên từ totalPrice thành subtotal cho đúng nghĩa "Thành tiền"

    // --- GETTER & SETTER ---

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    // 3. Quan trọng: Hàm Getter này sẽ giúp Mapper hết lỗi đỏ!
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}