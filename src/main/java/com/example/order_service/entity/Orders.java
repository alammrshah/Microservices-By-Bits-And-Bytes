package com.example.order_service.entity;

import com.example.order_service.dto.OrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

}
