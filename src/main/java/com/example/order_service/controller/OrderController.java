package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequestDto;
import com.example.order_service.dto.OrderResponseDto;
import com.example.order_service.dto.OrderStatusEnum;
import com.example.order_service.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;



    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.PlaceOrder(orderRequestDto);
        logger.info("Order placed successfully with ID: {}", orderResponseDto.getOrderId());
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrderById(orderId);
        if (orderResponseDto != null) {
            logger.info("Order retrieved successfully with ID: {}", orderId);
            return ResponseEntity.ok(orderResponseDto);
        } else {
            logger.error("Order not found with ID: {}", orderId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable String customerId) {
        List<OrderResponseDto> orders = orderService.getOrdersByCustomerId(customerId);
        if (orders != null && !orders.isEmpty()) {
            logger.info("Orders retrieved successfully for customer ID: {}", customerId);
            return ResponseEntity.ok(orders);
        } else {
            logger.error("No orders found for customer ID: {}", customerId);
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatusEnum status) {
        orderService.updateOrderStatus(orderId, status);
        logger.info("Order status updated successfully for order ID: {}", orderId);
        return ResponseEntity.ok("Order status updated successfully for order ID: " + orderId);
    }
}
