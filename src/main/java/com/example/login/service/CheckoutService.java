package com.example.login.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.dto.CartResponseDto;
import com.example.login.dto.CheckoutItemDto;
import com.example.login.dto.CheckoutResponseDto;
import com.example.login.mapper.CheckoutMapper;

@Service
@Transactional(readOnly = true)
public class CheckoutService {

    private final CartService cartService;
    private final CheckoutMapper checkoutMapper;

    public CheckoutService(CartService cartService, CheckoutMapper checkoutMapper) {
        this.cartService = cartService;
        this.checkoutMapper = checkoutMapper;
    }

    public CheckoutResponseDto getCheckoutSummary(String username) {

        CartResponseDto cart = cartService.getCartByUserName(username);

        if (cart == null || cart.getItems() == null) {
            return new CheckoutResponseDto(List.of(), BigDecimal.ZERO);
        }

        List<CheckoutItemDto> selectedItems = cart.getItems().stream()
                .filter(item -> Boolean.TRUE.equals(item.getIsSelected()))
                .map(checkoutMapper::toCheckoutItemDto)
                .collect(Collectors.toList());

        BigDecimal total = selectedItems.stream()
                .map(i -> i.getSubtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CheckoutResponseDto(selectedItems, total);
    }
}