package com.project.project.services;

import com.project.project.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long productId);
    void deleteProduct(Long productId);
    ProductDto updateProduct(Long productId, ProductDto productDto);
}
