package com.example.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ManufactureRequestDto {
	@NotBlank(message = "name must not be null")
	@Size(max = 20,message = "name must be <= 20")
    private String name;
	
	@NotBlank(message = "address must not be null")
    private String address;
	
	@NotNull(message = "phone number must be fill in")
    private String phone;
	
    @Email(message = "Please enter a valid email address ")
    private String email;
    
    @NotBlank(message = "please add to some description")
    private String description;
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
    
}
