package com.example.login.entity;

public enum OrderStatus {
    PENDING,    // Chờ xác nhận
    CONFIRMED,  // Đã xác nhận (Admin làm)
    SHIPPING,   // Đang giao hàng
    COMPLETED,  // Đã hoàn thành (Đã thu tiền)
    CANCELLED   // Đã hủy
}