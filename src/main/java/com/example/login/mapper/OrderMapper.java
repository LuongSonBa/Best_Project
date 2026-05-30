package com.example.login.mapper;

import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.login.dto.OrderItemResponseDto;
import com.example.login.dto.OrderResponseDto;
import com.example.login.entity.Order;
import com.example.login.entity.OrderItem;

@Component
public class OrderMapper {

    public OrderResponseDto toResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt()); 
        dto.setStatus(order.getStatus());
        dto.setPhoneNumber(order.getPhoneNumber()); 
        dto.setShippingAddress(order.getShippingAddress());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponseDto> itemDtos = order.getOrderItems().stream()
                .map(this::toItemDto)
                .toList();
        dto.setItems(itemDtos);

        return dto;
    }

    private OrderItemResponseDto toItemDto(OrderItem item) {
        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setComputerId(item.getComputer().getId());  
        dto.setComputerName(item.getComputer().getName()); 
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtPurchase(item.getPriceAtPurchase());

        if (item.getImage() != null && item.getImage().length > 0) {
            String base64String = Base64.getEncoder().encodeToString(item.getImage());
 
            dto.setImageBase64("data:image/png;base64," + base64String);
        } else {
            dto.setImageBase64("/images/default-pc.png"); 
        }
        return dto;
    }
}