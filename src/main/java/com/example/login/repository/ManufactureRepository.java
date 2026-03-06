package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Manufacture;
@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
	Optional<Manufacture> findByName(String name);
}
