package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequestDto;
import com.ecommerce.product_service.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(String productId);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto updateStock(String productId, Integer stockQuantity);
    void deleteProduct(String productId);

}
