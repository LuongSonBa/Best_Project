package com.example.login.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.login.dto.OrderItemResponseDto;
import com.example.login.dto.OrderRequestDto;
import com.example.login.dto.OrderResponseDto;
import com.example.login.entity.Cart;
import com.example.login.entity.CartItem;
import com.example.login.entity.Order;
import com.example.login.entity.OrderItem;
import com.example.login.entity.OrderStatus;
import com.example.login.entity.User;
import com.example.login.repository.CartItemRepository;
import com.example.login.repository.CartRepository;
import com.example.login.repository.OrderRepository;
import com.example.login.repository.UserRepository;
import com.example.login.spec.CartSpecification;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public OrderResponseDto createOrder(String username, OrderRequestDto request) {

        if (request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty() ||
            request.getShippingAddress() == null || request.getShippingAddress().trim().isEmpty()) {
            throw new RuntimeException("Shipping information is required!");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        Cart cart = cartRepository.findOne(CartSpecification.getCartWithDetails(username))
                .orElseThrow(() -> new RuntimeException("Your cart is empty!"));

        List<CartItem> selectedItems = cart.getCartItems().stream()
                .filter(CartItem::getIsSelected)
                .collect(Collectors.toList());

        if (selectedItems.isEmpty()) {
            throw new RuntimeException("No items selected in cart. Please select items before placing order.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setPhoneNumber(request.getPhoneNumber());
        order.setShippingAddress(request.getShippingAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
//List
        List<OrderItem> orderItems = selectedItems.stream().map(cartItem -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setComputer(cartItem.getComputer());
            oi.setQuantity(cartItem.getQuantity());
            oi.setPriceAtPurchase(cartItem.getComputer().getPrice()); //get price
            return oi;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        BigDecimal total = orderItems.stream()
        	    .map(oi -> oi.getPriceAtPurchase()
        	        .multiply(BigDecimal.valueOf(oi.getQuantity())))
        	    .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);

        // 8. Lưu vào DB và xóa các món đã mua khỏi giỏ hàng
        Order savedOrder = orderRepository.save(order);
        cartItemRepository.deleteAll(selectedItems);

        return convertToResponseDto(savedOrder);
    }

    private OrderResponseDto convertToResponseDto(Order order) {

    	List<OrderItemResponseDto> items = order.getOrderItems().stream()
    		    .map(oi -> new OrderItemResponseDto(
    		        oi.getComputer().getId(),        // ✅ thêm cái này
    		        oi.getComputer().getName(),
    		        oi.getQuantity(),
    		        oi.getPriceAtPurchase()
    		    ))
    		    .collect(Collectors.toList());

        return new OrderResponseDto(
            order.getId(),
            order.getCreatedAt(),
            order.getStatus(),
            order.getPhoneNumber(),
            order.getShippingAddress(),
            order.getTotalAmount(),
            items // ✅ FIX
        );
    }
}