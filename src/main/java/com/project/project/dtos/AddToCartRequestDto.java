package com.project.project.dtos;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private long productId;
    private Integer quantity;
}
