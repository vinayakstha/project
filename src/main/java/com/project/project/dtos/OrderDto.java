package com.project.project.dtos;

import com.project.project.enums.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;

    @NotBlank(message = "Price is mandatory")
    private BigDecimal price;

    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;
}
