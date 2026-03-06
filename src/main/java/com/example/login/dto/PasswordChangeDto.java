package com.example.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordChangeDto {

    @NotBlank(message = "Vui lòng nhập mật khẩu cũ.")
    private String oldPassword;

    @NotBlank(message = "Vui lòng nhập mật khẩu mới.")
    @Size(min = 6, message = "Mật khẩu mới phải có ít nhất 6 ký tự.")
    private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
