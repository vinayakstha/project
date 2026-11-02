package com.project.project.services;

import com.project.project.dtos.OrderDto;
import com.project.project.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder();
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long orderId);
    OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus);
    void cancelOrder(Long orderId);
}
