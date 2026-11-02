package com.project.project.controller;

import com.project.project.dtos.CartDto;
import com.project.project.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getCart());
    }

    @DeleteMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
    }

    @PostMapping("/{productId}/{quantity}")
    public ResponseEntity<CartDto> addToCart(@PathVariable Long productId,@PathVariable Integer quantity){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(productId, quantity));
    }

    @PutMapping("/{cartItemId}/{quantity}")
    public ResponseEntity<CartDto> updateQuantity(@PathVariable Long cartItemId, @PathVariable Integer quantity){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.updateQuantity(cartItemId, quantity));
    }

}
