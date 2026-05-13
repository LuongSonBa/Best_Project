package com.example.login.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;
import com.example.login.dto.CheckoutResponseDto;

@Service
@Transactional(readOnly = true)
public class CheckoutService {

    private final CartService cartService;

    public CheckoutService(CartService cartService) {
        this.cartService = cartService;
    }

    public CheckoutResponseDto getCheckoutSummary(String username) {

        CartResponseDto cart = cartService.getCartByUsername(username);

        if (cart == null || cart.getItems() == null) {
            return new CheckoutResponseDto(List.of(), BigDecimal.ZERO);
        }
        List<CartItemResponseDto> selectedItems = cart.getItems().stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsSelected()))
                .collect(Collectors.toList());

        BigDecimal total = selectedItems.stream()
                .map(item -> {
                    if (item.getSubtotal() != null) {
                        return item.getSubtotal();
                    }
                    return item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CheckoutResponseDto(selectedItems, total);
    }
}