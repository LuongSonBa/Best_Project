package com.example.login.service;

import org.springframework.stereotype.Service;
import com.example.login.dto.UserDto;
import com.example.login.entity.User;
import com.example.login.exception.NotFoundException;
import com.example.login.repository.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDto getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Người dùng không tồn tại"));

		return new UserDto(user.getUsername(), user.getId(), user.getRole());
	}

	@Transactional
	public String changePassword(String username, String oldPassword, String newPassword) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException ("Người dùng không tồn tại"));

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new RuntimeException("Mật khẩu cũ không đúng!");
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);

		return "Đổi mật khẩu thành công!";
	}
}