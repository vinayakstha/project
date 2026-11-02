package com.project.project.services.impl;

import com.project.project.dtos.CartDto;
import com.project.project.entities.Cart;
import com.project.project.entities.User;
import com.project.project.repositories.CartRepository;
import com.project.project.repositories.UserRepository;
import com.project.project.services.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDto getCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public void clearCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public CartDto addToCart(Long productId, Integer quantity) {
        return null;
    }

    @Override
    public CartDto updateQuantity(Long cartItemId, Integer quantity) {
        return null;
    }

    @Override
    public CartDto removeFromCart(Long cartItemId) {
        return null;
    }
}
