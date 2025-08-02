package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.CategoryRequestDto;
import com.ecommerce.product_service.dto.CategoryResponseDto;
import com.ecommerce.product_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return categoryService.createCategory(categoryRequestDto);
    }

    @GetMapping
    public List<CategoryResponseDto> getCategory() {
        return categoryService.getAllCategories();
    }
}
