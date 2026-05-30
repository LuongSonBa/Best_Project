package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.login.entity.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long>,
											JpaSpecificationExecutor<Computer>{
    @EntityGraph(attributePaths = "manufacture")
    List<Computer> findAll();

    @EntityGraph(attributePaths = "manufacture")
    List<Computer> findAll(Specification<Computer> spec);
}
