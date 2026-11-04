package com.project.project.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;

    @NotBlank(message = "Product name is mandatory")
    private String productName;

    @NotBlank(message = "Product description is mandatory")
    private String productDescription;

    @NotBlank(message = "Product price is mandatory")
    private Long productPrice;

    @NotBlank(message = "Product image is mandatory")
    private String productImage;

    @NotBlank(message = "Product category is mandatory")
    private Long categoryId;
}
