package com.ecommerce.product_service.dto;

import lombok.Data;

@Data
public class CategoryRequestDto {
    private String name;
    private String description;
    private String categoryId;
}
