package com.example.order_service.service;

import com.example.order_service.dto.ProductResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private static final Logger logger = LoggerFactory.getLogger(ProductClient.class);

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ProductResponseDto getProductName(String productId) {
        String url = "http://localhost:8081/products/" + productId;
        logger.info("Fetching product details from URL: {}", url);

        try {
            ProductResponseDto response = restTemplate.getForObject(url, ProductResponseDto.class);
            logger.info("Successfully fetched product details for productId: {}", productId);
            return response;
        } catch (HttpClientErrorException e) {
            logger.error("Client error while fetching product with productId {}: {}", productId, e.getMessage());
        } catch (HttpServerErrorException e) {
            logger.error("Server error while fetching product with productId {}: {}", productId, e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Resource access error (possibly connection issue) for productId {}: {}", productId, e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while fetching product with productId {}: {}", productId, e.getMessage(), e);
        }

        return null; // or you can throw a custom exception if preferred
    }


    public void upddateStock(String productId, int quantity) {
        String url = "http://localhost:8081/products/" + productId + "/stock?stockQuantity=" + quantity;
        restTemplate.patchForObject(url,quantity, Void.class);
    }
}
