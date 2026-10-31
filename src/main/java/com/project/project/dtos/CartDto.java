package com.project.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long cartId;
    private List<CartItemDto> cartItem;
    private Long totalPrice;
}
