package com.example.login.service;

import com.example.login.dto.OrderRequestDto;
import com.example.login.dto.OrderResponseDto;

public interface OrderService {
	OrderResponseDto createOrder(String username, OrderRequestDto request);
}
