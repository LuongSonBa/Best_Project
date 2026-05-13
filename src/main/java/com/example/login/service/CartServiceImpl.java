package com.example.login.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.dto.CartItemRequestDto;
import com.example.login.dto.CartItemResponseDto;
import com.example.login.dto.CartResponseDto;
import com.example.login.entity.Cart;
import com.example.login.entity.CartItem;
import com.example.login.entity.Computer;
import com.example.login.entity.User;
import com.example.login.exception.NotFoundException;
import com.example.login.mapper.CartItemMapper;
import com.example.login.repository.CartItemRepository;
import com.example.login.repository.CartRepository;
import com.example.login.repository.ComputerRepository;
import com.example.login.repository.UserRepository;
import com.example.login.spec.CartSpecification;
@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ComputerRepository computerRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
            ComputerRepository computerRepository, UserRepository userRepository, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.computerRepository = computerRepository;
        this.userRepository = userRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartItemResponseDto addToCart(String username, CartItemRequestDto request) {
        Cart cart = cartRepository.findOne(CartSpecification.getCartWithDetails(username))
        		.orElseGet(() -> {
        			User user = userRepository.findByUsername(username)
        					.orElseThrow(() -> new NotFoundException("User not found"));
        			return createNewCart(user.getId());
        		});
        Optional<CartItem> existingItem = cart.getCartItems().stream()
        		.filter(item -> item.getComputer().getId().equals(request.getComputerId()))
        		.findFirst();
        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            Computer computer = computerRepository.findById(request.getComputerId())
                    .orElseThrow(() -> new NotFoundException("ProductId is not found"));

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setComputer(computer);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setIsSelected(true);
            cart.getCartItems().add(cartItem);
        }
        
        cartItemRepository.save(cartItem);
        return cartItemMapper.toResponseDto(cartItem);
    }

    private Cart createNewCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User is not found"));

        Cart cart = new Cart();
        cart.setUser(user);

        return cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponseDto getCartByUsername(String username) {
        Cart cart = cartRepository.findOne(CartSpecification.getCartWithDetails(username))
        		.orElseGet(() -> {
        			User user = userRepository.findByUsername(username)
        					.orElseThrow(() -> new NotFoundException("User not found"));
        			return createNewCart(user.getId());
        		});
        return cartItemMapper.toCartResponseDto(cart.getCartItems());
    }

    @Override
    @Transactional
    public CartResponseDto updateFullCart(String username, List<CartItemRequestDto> requests) {
        Cart cart = cartRepository.findOne(CartSpecification.getCartWithDetails(username))
        		.orElseThrow(() -> new NotFoundException("Cart Not Found"));
        for (CartItemRequestDto req : requests) {
            cart.getCartItems().stream()
                .filter(item -> item.getId().equals(req.getCartItemId()))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(req.getQuantity());
                    item.setIsSelected(req.getIsSelected());
                });
        }
        return cartItemMapper.toCartResponseDto(cart.getCartItems());
    }

    @Override
    @Transactional
    public void deleteItem(String username, Long itemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found"));

        if (item.getCart().getUser().getId().equals(user.getId())) {
            cartItemRepository.deleteById(itemId);
        } else {
            throw new RuntimeException("Unauthorized to delete this item");
        }
    }
}