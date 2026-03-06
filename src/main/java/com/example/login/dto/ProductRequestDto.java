package com.example.login.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {
	@NotBlank(message = "Name cannot empty")
	private String name;
	
	@Min(value = 0, message = "price must be >= 0")
	private BigDecimal price;
	
	@Size (max = 255, message = "Desciption is too long")
	private String description;
	
	private Long manufactureId;
	
	private Boolean active;
	public Long id;
	public ProductRequestDto() {}

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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
