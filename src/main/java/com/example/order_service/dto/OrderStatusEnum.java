package com.example.order_service.dto;

public enum OrderStatusEnum {

    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED;

    public static OrderStatusEnum fromString(String status) {
        for (OrderStatusEnum orderStatus : OrderStatusEnum.values()) {
            if (orderStatus.name().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Unknown order status: " + status);
    }
}
