package com.example.login.controller; // Hoặc package api tùy bạn đặt

import com.example.login.dto.CheckoutResponseDto;
import com.example.login.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutApiController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/data")
    public ResponseEntity<CheckoutResponseDto> getCheckoutData(@AuthenticationPrincipal UserDetails userDetails) {
        // Lấy username từ SecurityContext và gọi Service
        CheckoutResponseDto response = checkoutService.getCheckoutSummary(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }
}