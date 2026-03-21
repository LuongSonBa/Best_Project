package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.login.entity.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long>,
											JpaSpecificationExecutor<Computer>{

}
