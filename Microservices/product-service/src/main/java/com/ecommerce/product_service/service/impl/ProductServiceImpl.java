package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductRequestDto;
import com.ecommerce.product_service.dto.ProductResponseDto;
import com.ecommerce.product_service.entity.Category;
import com.ecommerce.product_service.entity.Product;
import org.springframework.stereotype.Service;
import com.ecommerce.product_service.repository.CategoryRepository;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        // Find the category
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + productRequestDto.getCategoryId()));
        
        // Create and save the product
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setStockQuantity(productRequestDto.getStockQuantity());
        product.setCategory(category);
        
        Product savedProduct = productRepository.save(product);

        return convertToDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(String productId) {
        Product product =  productRepository.getById(productId);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id: " + productId);
        }
        ProductResponseDto productResponseDto = convertToDto(product);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ProductResponseDto updateStock(String productId, Integer stockQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
        product.setStockQuantity(stockQuantity);
        Product savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    /**
     * Converts a Product entity to a ProductRequestDto
     * @param product the Product entity to convert
     * @return ProductRequestDto containing the product data
     */
    private ProductResponseDto convertToDto(Product product) {
        if (product == null) return null;
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setInStock(product.getInStock());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
}
