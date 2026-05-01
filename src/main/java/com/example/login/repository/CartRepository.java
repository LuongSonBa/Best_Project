package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.login.entity.Cart;

	public interface CartRepository extends JpaRepository<Cart, Long>,JpaSpecificationExecutor<Cart> {
		Optional<Cart> findByUserId(Long userId);
	}