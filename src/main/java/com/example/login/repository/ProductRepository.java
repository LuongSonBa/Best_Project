package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}