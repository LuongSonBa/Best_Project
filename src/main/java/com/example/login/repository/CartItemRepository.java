	package com.example.login.repository;
	
	import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.CartItem;
	
	public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	    Optional<CartItem> findByCart_IdAndComputer_Id(Long cartId, Long computerId);

	    List<CartItem> findByCart_Id(Long cartId);
	
	}
