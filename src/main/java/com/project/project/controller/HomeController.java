package com.project.project.controller;

import org.springframework.ui.Model;
import com.project.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "home";
    }
}
