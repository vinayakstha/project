package com.project.project.dtos;

import com.project.project.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateOrderStatusDto {

    @NotBlank(message = "Order status is mandatory")
    private OrderStatus orderStatus;
}