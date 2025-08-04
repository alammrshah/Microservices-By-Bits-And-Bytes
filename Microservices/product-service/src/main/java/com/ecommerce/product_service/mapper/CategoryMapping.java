package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.CategoryRequestDto;
import com.ecommerce.product_service.dto.CategoryResponseDto;
import com.ecommerce.product_service.dto.ProductResponseDto;
import com.ecommerce.product_service.entity.Category;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryMapping {

    @Autowired
    public  ProductRepository productRepository;

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
            if (category.getProducts() != null) {
                List<ProductResponseDto> productDtos = category.getProducts()
                        .stream()
                        .map(product -> {
                            ProductResponseDto dto = new ProductResponseDto();
                            dto.setProductId(product.getProductId());
                            dto.setName(product.getName());
                            dto.setDescription(product.getDescription());
                            dto.setPrice(product.getPrice());
                            dto.setStockQuantity(product.getStockQuantity());
                            dto.setInStock(product.getInStock());
                            dto.setCategoryName(product.getCategory().getName());
                            return dto;
                        })
                        .toList();

                responseDto.setProducts(productDtos);
            }

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
