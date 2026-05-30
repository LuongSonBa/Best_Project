package com.example.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.OrderRequestDto;
import com.example.login.dto.OrderResponseDto;
import com.example.login.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    @Autowired
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderApiController.class);


    @PostMapping("/create")
    public ResponseEntity<?> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails, 
            @RequestBody OrderRequestDto request) {

        if (userDetails == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        try {
            OrderResponseDto response = orderService.createOrder(userDetails.getUsername(), request);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            logger.warn("Business error while placing order: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected error while placing order", e);
            return ResponseEntity.internalServerError().body("An error occurred during order placement.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        try {
            if (userDetails == null) {
                return ResponseEntity.status(401).body("User not authenticated");
            }

            OrderResponseDto response = orderService.getOrderDetail(id, userDetails.getUsername());
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi lấy chi tiết đơn hàng");
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id) {
        try {
            if (userDetails == null) {
                return ResponseEntity.status(401).body("User not authenticated");
            }

            orderService.cancelOrder(id, userDetails.getUsername());
            return ResponseEntity.ok("Đã hủy đơn hàng thành công!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Lỗi khi hủy đơn hàng");
        }
    }
}