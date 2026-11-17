
package com.project.project.controller;

import com.project.project.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderControllerJSP {

    private final OrderService orderService;

    @PostMapping("/place-order")
    public String placeOrder() {

        orderService.placeOrder();

        return "redirect:/order-confirmation";
    }

    @GetMapping("/order-confirmation")
    public String orderConfirmation() {

        return "order-confirmation";
    }

}
