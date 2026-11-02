package com.project.project.services.impl;

import com.project.project.dtos.OrderDto;
import com.project.project.enums.OrderStatus;
import com.project.project.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto placeOrder() {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
