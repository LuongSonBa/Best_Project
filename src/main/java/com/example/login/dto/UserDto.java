package com.example.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDto {

	@NotBlank
	private String userName;

	private Long id;

	@NotNull
	private String role;
	private String phoneNumber; // Thêm mới
    private String address;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserDto(String userName, Long id, String role, String phoneNumer, String address) {
		this.userName = userName;
		this.id = id;
		this.role = role;
		this.address = address;
		this.phoneNumber = phoneNumer;

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
