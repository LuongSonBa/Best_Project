package com.example.login.dto;

import java.math.BigDecimal;

public class ComputerResponseDto {
	private Long id;
    private String name;
    private BigDecimal price;
    private String formattedPrice; 
    private String description;
    private String imagePath; 
    private Long manufactureId;
    private String manufactureName;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getManufactureId() {
		return manufactureId;
	}
	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
	}
	public String getManufactureName() {
		return manufactureName;
	}
	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

    // Getters and Setters
    
}
