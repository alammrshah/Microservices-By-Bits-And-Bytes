package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.CategoryRequestDto;
import com.ecommerce.product_service.dto.CategoryResponseDto;
import com.ecommerce.product_service.entity.Category;
import com.ecommerce.product_service.mapper.CategoryMapping;
import com.ecommerce.product_service.repository.CategoryRepository;
import com.ecommerce.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapping.toCategoryResponseDto(savedCategory);
    }

    @Override
    public CategoryRequestDto updateCategory(String categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return CategoryMapping.toCategoryRequestDto(updatedCategory);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        categoryRepository.delete(category);
        // Optionally, you can return a response or void if deletion is successful

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapping::toCategoryResponseDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
    return CategoryMapping.toCategoryResponseDto(category);}
}
