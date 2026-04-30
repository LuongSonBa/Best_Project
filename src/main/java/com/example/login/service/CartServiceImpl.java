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
import com.example.login.entity.Product;
import com.example.login.entity.User;
import com.example.login.exception.NotFoundException;
import com.example.login.mapper.CartItemMapper;
import com.example.login.repository.CartItemRepository;
import com.example.login.repository.CartRepository;
import com.example.login.repository.ComputerRepository;
import com.example.login.repository.UserRepository;

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
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.computerRepository = computerRepository;
		this.userRepository = userRepository;
		this.cartItemMapper = cartItemMapper;
	}



	@Override
	public CartItemResponseDto addToCart(Long userId, CartItemRequestDto request) {
		Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> createNewCart(userId));
		
		Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndComputerId(cart.getId(), request.getComputerId());
		
		CartItem cartItem;
		
		if (existingItem.isPresent()) {
			cartItem = existingItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
		}
		else {
			Computer computer = computerRepository.findById(request.getComputerId()).orElseThrow(() -> new NotFoundException("ProductId is not found"));
			
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setComputer(computer);
			cartItem.setQuantity(request.getQuantity());
			cartItem.setIsSelected(true);
			
			}
		cartItemRepository.save(cartItem);
		return cartItemMapper.toResponseDto(cartItem);
			
	}
	private Cart createNewCart(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));

		Cart cart = new Cart();
		cart.setUser(user);

		return cartRepository.save(cart);
	}
	@Override
	@Transactional(readOnly = true)
	public CartResponseDto getCartByUserId(Long userId) {
	    Cart cart = cartRepository.findByUserId(userId)
	            .orElseGet(() -> {
	                Cart newCart = new Cart();
	                return cartRepository.save(newCart);
	            });

	    List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
	    return cartItemMapper.toCartResponseDto(items);
	}
	
	@Override
	@Transactional
	public CartResponseDto updateFullCart(Long userId, List<CartItemRequestDto> requests) {
	    Cart cart = cartRepository.findByUserId(userId)
	            .orElseThrow(() -> new NotFoundException("Cart not found"));

	    for (CartItemRequestDto req : requests) {
	        CartItem item = cartItemRepository.findById(req.getCartItemId())
	                .orElseThrow(() -> new NotFoundException("Item not found: " + req.getCartItemId()));

	        // Bảo mật: Chỉ cho phép sửa nếu item thuộc về đúng giỏ hàng của User
	        if (item.getCart().getId().equals(cart.getId())) {
	            item.setQuantity(req.getQuantity());
	            item.setIsSelected(req.getIsSelected());
	            cartItemRepository.save(item);
	        }
	    }
	    // Gọi lại hàm lấy giỏ hàng để Mapper tính toán lại TotalPrice
	    return getCartByUserId(userId);
	}

	@Override
	@Transactional
	public void deleteItem(Long itemId) {
	    cartItemRepository.deleteById(itemId);
	}
}