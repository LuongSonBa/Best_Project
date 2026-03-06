package com.example.login.dto;

import java.math.BigDecimal;

public class ComputerRequestDto {
	private String name;
    private BigDecimal price;
    private String description;
    private Long manufactureId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}
    
}
