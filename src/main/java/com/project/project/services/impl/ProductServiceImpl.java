package com.project.project.services.impl;

import com.project.project.dtos.ProductDto;
import com.project.project.entities.Category;
import com.project.project.entities.Product;
import com.project.project.entities.User;
import com.project.project.repositories.CategoryRepository;
import com.project.project.repositories.ProductRepository;
import com.project.project.repositories.UserRepository;
import com.project.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        product.setUser(user);

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return modelMapper.map(product, ProductDto.class);
    }
}
