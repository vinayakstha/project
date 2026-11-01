package com.project.project.services;

import com.project.project.dtos.CartDto;

public interface CartService {
    CartDto getCart();
    void clearCart();
    CartDto addToCart(Long productId, Integer quantity);
    CartDto updateQuantity(Long cartItemId, Integer quantity);
    CartDto removeFromCart(Long cartItemId);
}
