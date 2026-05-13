package com.example.login.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private String phoneNumber;     // Số điện thoại nhận hàng
    private String shippingAddress; // Địa chỉ nhận hàng
    // Bạn có thể thêm note (ghi chú) nếu muốn
    private String note;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	} 
    
}