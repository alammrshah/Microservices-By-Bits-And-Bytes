package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.CategoryRequestDto;
import com.ecommerce.product_service.dto.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryRequestDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto);
    void deleteCategory(String categoryId);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto getCategoryById(String categoryId);

}
