package com.project.project.services;

import com.project.project.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long categoryId);
    void deleteCategory(Long categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
}
