package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.entity.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

}
