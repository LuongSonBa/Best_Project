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
import com.example.login.entity.Product;
import com.example.login.entity.User;
import com.example.login.exception.NotFoundException;
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

	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			ProductRepository productRepository, UserRepository userRepository, CartItemMapper cartItemMapper) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.cartItemMapper = cartItemMapper;
	}

	@Override
	public CartItemResponseDto addToCart(Long userId, CartItemRequestDto request) {
		Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> createNewCart(userId));
		
		Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.getProductId());
		
		CartItem cartItem;
		
		if (existingItem.isPresent()) {
			cartItem = existingItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
		}
		else {
			Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new NotFoundException("ProductId is not found"));
			
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(request.getQuantity());
			cartItem.setIsSelected(true);
			
			}
		cartItemRepository.save(cartItem);
		return cartItemMapper.toResponseDto(cartItem);
		
	}
	@Override
	@Transactional(readOnly = true)
	public CartResponseDto getCartByUserId(Long userId) {
	    // 1. Lấy giỏ hàng của User (Nếu chưa có thì tạo mới)
	    Cart cart = cartRepository.findByUserId(userId)
	            .orElseGet(() -> {
	                Cart newCart = new Cart();
	                // Giả sử bạn đã có logic set User cho Cart ở đây
	                return cartRepository.save(newCart);
	            });

	    // 2. Lấy danh sách sản phẩm trong giỏ
	    List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

	    // 3. Dùng Mapper chuyển thành DTO để trả về cho Controller
	    // Hàm toCartResponseDto này chính là hàm gom List và tính TotalPrice bạn vừa sửa lỗi xong
	    return cartItemMapper.toCartResponseDto(items);
	}
	private Cart createNewCart(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User is not found"));

		Cart cart = new Cart();
		cart.setUser(user);

		return cartRepository.save(cart);
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