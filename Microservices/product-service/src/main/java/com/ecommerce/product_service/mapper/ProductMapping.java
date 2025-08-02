package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.ProductResponseDto;
import com.ecommerce.product_service.entity.Product;

public class ProductMapping {

    public static ProductResponseDto toProductResponseDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setProductId(product.getProductId());
        responseDto.setName(product.getName());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setCategoryName(product.getCategory().getName());
        responseDto.setInStock(product.getInStock());
        return responseDto;
    }

    public static Product toProductEntity(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setProductId(productResponseDto.getProductId());
        product.setName(productResponseDto.getName());
        product.setDescription(productResponseDto.getDescription());
        product.setPrice(productResponseDto.getPrice());
        product.setStockQuantity(product.getStockQuantity());
        product.setInStock(productResponseDto.getInStock());
        return product;
    }

}
