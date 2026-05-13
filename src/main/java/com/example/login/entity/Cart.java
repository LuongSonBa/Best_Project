package com.example.login.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

	    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user; // 1 User có 1 Cart
	    

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CartItem> cartItems; // Tên biến này phải khớp với Specification
	    

		public List<CartItem> getCartItems() {
			return cartItems;
		}

		public void setCartItems(List<CartItem> cartItems) {
			this.cartItems = cartItems;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Long getId() {
			return id;
		}

		
	}

