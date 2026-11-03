package com.project.project.dtos;

import com.project.project.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusDto {
    private OrderStatus orderStatus;
}