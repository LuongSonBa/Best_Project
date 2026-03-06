package com.example.login.dto;

import java.math.BigDecimal;

public class ProductResponseDto {

	private String name;

	private BigDecimal price; 

	private String description;

	private Long manufactureId;

	private Boolean active;
	private String ManufactureName;
	private String imagePath;
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getManufactureName() {
		return ManufactureName;
	}
	public void setManufactureName(String manufactureName) {
		ManufactureName = manufactureName;
	}
	public Long id;
	
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
