package com.example.login.dto;

import java.util.List;

public class ManufactureResponseDto {
	private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    private List<ProductResponseDto> products;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductResponseDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponseDto> products) {
		this.products = products;
	}
    
}
