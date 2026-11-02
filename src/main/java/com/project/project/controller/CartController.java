package com.project.project.controller;

import com.project.project.dtos.CartDto;
import com.project.project.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

}
