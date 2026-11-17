package com.project.project.controller;

import com.project.project.dtos.OrderDto;
import com.project.project.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {

        List<OrderDto> orders = orderService.getAllOrders();

        model.addAttribute("orders", orders);

        return "admin";
    }
}