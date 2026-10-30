package com.project.project.dtos;

import com.project.project.entities.Category;
import com.project.project.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private Long productPrice;
    private String productImage;
    private Long categoryId;
}
