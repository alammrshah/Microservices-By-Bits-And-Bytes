package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.CategoryRequestDto;
import com.ecommerce.product_service.dto.CategoryResponseDto;
import com.ecommerce.product_service.entity.Category;

public class CategoryMapping {

        public static CategoryRequestDto toCategoryRequestDto(Category category) {
            CategoryRequestDto requestDto = new CategoryRequestDto();
            requestDto.setCategoryId(category.getCategoryId());
            requestDto.setName(category.getName());
            requestDto.setDescription(category.getDescription());
            return requestDto;
        }

        public static CategoryResponseDto toCategoryResponseDto(Category category) {
            CategoryResponseDto responseDto = new CategoryResponseDto();
            responseDto.setCategoryId(category.getCategoryId());
            responseDto.setName(category.getName());
            responseDto.setDescription(category.getDescription());
            return responseDto;
        }

        public static Category toCategoryEntity(CategoryRequestDto requestDto) {
            Category category = new Category();
            category.setCategoryId(requestDto.getCategoryId());
            category.setName(requestDto.getName());
            category.setDescription(requestDto.getDescription());
            return category;
        }
}
