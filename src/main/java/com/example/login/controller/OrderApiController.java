package com.example.login.controller;

import com.example.login.dto.OrderRequestDto;
import com.example.login.dto.OrderResponseDto;
import com.example.login.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails, 
            @RequestBody OrderRequestDto request) {
        
        try {
            if (userDetails == null) {
                return ResponseEntity.status(401).body("User not authenticated");
            }

            OrderResponseDto response = orderService.createOrder(userDetails.getUsername(), request);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Trả về lỗi hệ thống không mong muốn
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An error occurred during order placement.");
        }
    }
}