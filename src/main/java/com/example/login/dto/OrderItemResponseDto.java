package com.example.login.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {
    private Long computerId;      // ID sản phẩm
    private String computerName;  // Tên máy tính
    private Integer quantity;     // Số lượng đã mua
    private BigDecimal priceAtPurchase; // Khớp với Entity cho đỡ nhầm  
    // Giá tại thời điểm mua (quan trọng!)
    
	public Long getComputerId() {
		return computerId;
	}
	public OrderItemResponseDto(Long computerId, String computerName, Integer quantity, BigDecimal priceAtPurchase) {
		super();
		this.computerId = computerId;
		this.computerName = computerName;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
	}
	public void setComputerId(Long computerId) {
		this.computerId = computerId;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceAtPurchase() {
		return priceAtPurchase;
	}
	public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}
	
    
}