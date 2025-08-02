package com.ecommerce.product_service.dto;

import com.ecommerce.product_service.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) // ✅ This is all you need
public class CategoryResponseDto {
    private String categoryId;
    private String name;
    private String description;
    private List<Product> products;
}
