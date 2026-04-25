package com.example.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

