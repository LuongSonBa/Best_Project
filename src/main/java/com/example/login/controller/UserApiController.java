package com.example.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.PasswordChangeDto;
import com.example.login.dto.UserDto;
import com.example.login.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Api Controller")
public class UserApiController {

	private final UserService userService;

	public UserApiController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/info")
	public UserDto getCurrentUserInfo(Authentication authentication) {
		String username = authentication.getName();	
		return userService.getUserByUsername(username);
	}

	@PutMapping("/change-password")
	public ResponseEntity<String> changePassword(@AuthenticationPrincipal UserDetails principal,
			@Valid @RequestBody PasswordChangeDto request) {
		System.out.println("Principal = " + principal);
		try {
			String username = principal.getUsername(); // Lấy user đang login
			String result = userService.changePassword(username, request.getOldPassword(), request.getNewPassword());
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
}
