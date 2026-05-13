package com.example.login.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.login.entity.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;                        // Mã đơn hàng
    private LocalDateTime createdAt;        // Ngày đặt
    private OrderStatus status;                  // Trạng thái (PENDING, SHIPPING, DONE...)
    private String phoneNumber;
    private String shippingAddress;
    private BigDecimal totalAmount;         // Tổng tiền cuối cùng
    private List<OrderItemResponseDto> items; // Chi tiết từng món trong đơn
    
    
	public OrderResponseDto(Long id, LocalDateTime createdAt, OrderStatus status, String phoneNumber,
			String shippingAddress, BigDecimal totalAmount, List<OrderItemResponseDto> items) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
		this.totalAmount = totalAmount;
		this.items = items;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<OrderItemResponseDto> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponseDto> items) {
		this.items = items;
	}
    
}