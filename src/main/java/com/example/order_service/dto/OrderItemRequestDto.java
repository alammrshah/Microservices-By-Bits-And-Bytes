package com.example.order_service.dto;

import lombok.Data;

@Data
public class OrderItemRequestDto {

    private String productId;
    private Integer quantity;

}
