package com.example.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") 
public class User extends BaseEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // 

    @Column(nullable = false, unique = true) 
    private String username;

    @Column(nullable = false) 
    private String password;
    
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
	@Column(nullable = false) 
    private String role = "ROLE_USER";
    private String phoneNumber; // Thêm mới
    private String address;


    public User() {}


    public User(String username, String password,String role) {
        this.username = username;	
        this.password = password;
        this.role = role;
        
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Getter - Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}