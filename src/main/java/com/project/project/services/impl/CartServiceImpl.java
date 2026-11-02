package com.project.project.services.impl;

import com.project.project.dtos.CartDto;
import com.project.project.entities.Cart;
import com.project.project.entities.CartItem;
import com.project.project.entities.Product;
import com.project.project.entities.User;
import com.project.project.repositories.CartItemRepository;
import com.project.project.repositories.CartRepository;
import com.project.project.repositories.ProductRepository;
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
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CartItemRepository cartItemRepository;

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        if(quantity == null || quantity <= 0){
            throw new RuntimeException("Quantity should be greater than zero");
        }

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if(cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else{
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setCart(cart);
            cart.getCartItems().add(newCartItem);
        }

        return getCart();
    }

    @Override
    public CartDto updateQuantity(Long cartItemId, Integer quantity) {
        if(quantity == null || quantity <= 0){
            throw new RuntimeException("Quantity should be greater than zero");
        }
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(quantity);
        return getCart();
    }

    @Override
    public CartDto removeFromCart(Long cartItemId) {
        return null;
    }
}
