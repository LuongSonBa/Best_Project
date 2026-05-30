package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    // Fetch sâu từ Order -> OrderItems -> Computer (Product)
    @EntityGraph(attributePaths = {"orderItems", "orderItems.computer"})
    Optional<Order> findWithDetailsById(Long id);
}