package com.project.project.services.impl;

import com.project.project.dtos.OrderDto;
import com.project.project.dtos.OrderItemDto;
import com.project.project.entities.*;
import com.project.project.enums.OrderStatus;
import com.project.project.repositories.CartRepository;
import com.project.project.repositories.OrderRepository;
import com.project.project.repositories.UserRepository;
import com.project.project.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderDto placeOrder() {

        // Get currently logged-in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.warn("User not found with email: {}", email);
            return new RuntimeException("User not found");
        });

        // Find user's cart
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> {
            log.warn("Cart not found");
            return new RuntimeException("Cart not found");
        });

        // Check if cart is empty
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cannot place order. Cart is empty");
        }

        // Create order
        Order order = Order.builder()
                .user(user)
                .orderStatus(OrderStatus.PENDING)
                .totalPrice(BigDecimal.ZERO)
                .orderItems(new ArrayList<>())
                .build();

        BigDecimal totalPrice = BigDecimal.ZERO;

        // Convert cart items into order items
        for (CartItem cartItem : cart.getCartItems()) {

            Product product = cartItem.getProduct();

            BigDecimal price = BigDecimal.valueOf(product.getProductPrice());

            BigDecimal subtotal = price.multiply(
                    BigDecimal.valueOf(cartItem.getQuantity())
            );

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .price(price)
                    .build();

            order.getOrderItems().add(orderItem);

            totalPrice = totalPrice.add(subtotal);
        }

        // Set total price
        order.setTotalPrice(totalPrice);

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear user's cart
        cart.getCartItems().clear();

        // Map order items to DTOs
        List<OrderItemDto> orderItemDtos = savedOrder.getOrderItems()
                .stream()
                .map(item -> OrderItemDto.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProduct().getProductId())
                        .productName(item.getProduct().getProductName())
                        .productImage(item.getProduct().getProductImage())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        ))
                        .build())
                .toList();

        // Map order to DTO
        return OrderDto.builder()
                .orderId(savedOrder.getOrderId())
                .price(savedOrder.getTotalPrice())
                .orderStatus(savedOrder.getOrderStatus())
                .orderItems(orderItemDtos)
                .build();
    }

    @Override
    public List<OrderDto> getAllOrders() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(()->{
            log.warn("User now found with email: {}", email);
            return new RuntimeException("User not found");
        });

        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(order -> {

                    List<OrderItemDto> orderItemDtos = order.getOrderItems()
                            .stream()
                            .map(item -> OrderItemDto.builder()
                                    .orderItemId(item.getOrderItemId())
                                    .productId(item.getProduct().getProductId())
                                    .productName(item.getProduct().getProductName())
                                    .productImage(item.getProduct().getProductImage())
                                    .price(item.getPrice())
                                    .quantity(item.getQuantity())
                                    .subtotal(item.getPrice().multiply(
                                            BigDecimal.valueOf(item.getQuantity())
                                    ))
                                    .build())
                            .toList();

                    return OrderDto.builder()
                            .orderId(order.getOrderId())
                            .price(order.getTotalPrice())
                            .orderStatus(order.getOrderStatus())
                            .orderItems(orderItemDtos)
                            .build();
                })
                .toList();
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(()->{
            log.warn("User not found with email: {}", email);
            return new RuntimeException("User not found");
        });

        Order order = orderRepository.findById(orderId).orElseThrow(()->{
            log.warn("Order not found with ID: {}", orderId);
            return new RuntimeException("Order not found");
        });

        if (!order.getUser().getUserId().equals(user.getUserId())) {
            log.warn("User {} attempted to access another user's order", email);
            throw new RuntimeException("You are not authorized to access this order");
        }

        // Convert order items into DTOs
        List<OrderItemDto> orderItemDtos = order.getOrderItems()
                .stream()
                .map(item -> OrderItemDto.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProduct().getProductId())
                        .productName(item.getProduct().getProductName())
                        .productImage(item.getProduct().getProductImage())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(item.getPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        ))
                        .build())
                .toList();

        // Return order DTO
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .price(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .orderItems(orderItemDtos)
                .build();
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
