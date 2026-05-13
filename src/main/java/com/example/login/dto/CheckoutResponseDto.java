package com.example.login.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Cần thiết để Jackson map JSON
public class CheckoutResponseDto {
    private List<CartItemResponseDto> items;
    private BigDecimal total;

    // Viết thủ công Constructor này để Eclipse chắc chắn nhận diện được
    public CheckoutResponseDto(List<CartItemResponseDto> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }
}