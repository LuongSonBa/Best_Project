	package com.example.login.repository;
	
	import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.CartItem;
	
	public interface CartItemRepository extends JpaRepository<CartItem, Long>{
		Optional<CartItem> findByCartIdAndComputerId(Long cartId, Long computerId);
		List<CartItem> findByCartId(Long cartId);
	
	}
