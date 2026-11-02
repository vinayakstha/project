package com.project.project.services.impl;

import com.project.project.dtos.CategoryDto;
import com.project.project.entities.Category;
import com.project.project.repositories.CategoryRepository;
import com.project.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        log.info("Category successfully created");
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        log.info("Getting all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Successfully retrieved all the categories. Total number of categories: {}", categories.size());
        return categories
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
                    log.warn("Category with ID: {} not found", categoryId);
                    return new RuntimeException("Category not found");
                }
        );
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->{
            log.warn("Category with ID: {} not found", categoryId);
            return new RuntimeException("Category not found");
        });
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> {
            log.warn("Category with ID: {} not found.", categoryId);
            return new RuntimeException("Category not found");
        });
        if(categoryDto.getCategoryName() != null) existingCategory.setCategoryName(categoryDto.getCategoryName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        log.info("Category updated successfully");
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }
}
