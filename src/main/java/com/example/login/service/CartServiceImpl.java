package com.example.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.entity.Cart;
import com.example.login.entity.CartItem;
import com.example.login.entity.Product;
import com.example.login.entity.User;
import com.example.login.mapper.CartItemMapper;
import com.example.login.repository.CartItemRepository;
import com.example.login.repository.CartRepository;
import com.example.login.repository.ProductRepository;
import com.example.login.repository.UserRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            CartItemMapper cartItemMapper
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartItemResponseDto addToCart(
            Long userId,
            CartItemRequestDto request
    ) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createNewCart(userId));

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartIdAndProductId(
                        cart.getId(),
                        request.getProductId()
                );

        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(
                    cartItem.getQuantity() + request.getQuantity()
            );
        } else {
            Product product = productRepository.findById(
                    request.getProductId()
            ).orElseThrow();

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setIsSelected(true);
        }

        cartItemRepository.save(cartItem);

        return cartItemMapper.toResponseDto(cartItem);
    }

    private Cart createNewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }
}