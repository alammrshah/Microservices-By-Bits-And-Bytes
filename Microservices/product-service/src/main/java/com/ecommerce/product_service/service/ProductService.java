package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequestDto;
import com.ecommerce.product_service.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductRequestDto getProductById(Long productId);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateStock(String productId, Integer stockQuantity);

}
