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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

        log.debug("Saving product to database");

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully. ProductID: {}", savedProduct.getProductId());

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("getting all products");

        List<Product> products = productRepository.findAll();

        log.info("successfully retrieved all products. Total number of Products: {}", products.size());

        return products
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        log.info("getting product by id");
        Product product = productRepository.findById(productId).orElseThrow(()-> {
            log.warn("product not found");
            return new RuntimeException("product not found");
        });
        log.debug("Product retrieved successfully from the database");

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->{
            log.warn("product now found");
            return new RuntimeException("product not found");
        });
        log.info("deleting product from the database");
        productRepository.delete(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(
                        () -> {
                            log.warn("Product not found");
                            return new RuntimeException("Product not found");
                        });

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(
                        () -> {
                            log.warn("Category not found");
                            return new RuntimeException("Category not found");
                        });

        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setProductDescription(productDto.getProductDescription());
        existingProduct.setProductPrice(productDto.getProductPrice());
        existingProduct.setProductImage(productDto.getProductImage());
        existingProduct.setCategory(category);

        Product updatedProduct = productRepository.save(existingProduct);
        log.info("Product updated");

        ProductDto response = modelMapper.map(updatedProduct, ProductDto.class);
        response.setProductId(updatedProduct.getProductId());
        response.setCategoryId(updatedProduct.getCategory().getCategoryId());

        return response;
    }
}
