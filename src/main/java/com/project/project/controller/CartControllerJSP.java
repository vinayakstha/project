package com.project.project.controller;

import com.project.project.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartControllerJSP {

    private final CartService cartService;

    @GetMapping("/cart")
    public String cartPage(Model model) {

        model.addAttribute("cart", cartService.getCart());

        return "cart";
    }

    @PostMapping("/cart/{productId}/{quantity}")
    public String addToCart(@PathVariable Long productId,
                            @PathVariable Integer quantity) {

        cartService.addToCart(productId, quantity);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cart", cartService.getCart());
        return "checkout";
    }
}