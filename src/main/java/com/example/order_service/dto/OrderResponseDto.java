package com.example.order_service.dto;

import com.example.order_service.entity.OrderItem;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatusEnum status;
    private List<OrderItem> items;
}
