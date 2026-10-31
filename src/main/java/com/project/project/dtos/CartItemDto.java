package com.project.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private Long productPrice;
    private String productImage;
    private Integer quantity;
    private Long subTotal;
}
