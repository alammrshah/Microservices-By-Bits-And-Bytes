package com.example.order_service.service;

import com.example.order_service.dto.*;
import com.example.order_service.entity.Orders;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderItemRepository;
import com.example.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
   private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * Places an order based on the provided order request DTO.
     *
     * @param orderRequestDto the order request containing customer ID and items to order
     * @return OrderResponseDto containing details of the placed order
     */
    public OrderResponseDto PlaceOrder(OrderRequestDto orderRequestDto) {

        String orderId = generateOrderId();
        double totalAmount = 0.0;

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestDto itemRequest : orderRequestDto.getItems()){
            ProductResponseDto product = productClient.getProductName(itemRequest.getProductId());
            if(product.getStockQuantity()<itemRequest.getQuantity()){
                logger.error("Insufficient stock for product: {}", product.getName());
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            //we add this - to the order item request
            //when user purchase a quantity of product, we reduce the stock quantity
            productClient.upddateStock(itemRequest.getProductId(),-itemRequest.getQuantity());
            double iteTotal = itemRequest.getQuantity()* product.getPrice();
            totalAmount = totalAmount + iteTotal;

            OrderItem orderItem = new OrderItem(generateOrderItemId(),orderId,itemRequest.getProductId(),itemRequest.getQuantity(),
                    product.getPrice());
            orderItems.add(orderItem);
        }

        Orders order = new Orders(orderId, orderRequestDto.getCustomerId(), LocalDateTime.now(), totalAmount,
                OrderStatusEnum.PENDING);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return new OrderResponseDto(order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount(),
                order.getStatus(), orderItems);

    }

    /**
     * Retrieves all orders placed in the system.
     *
     * @return List of OrderResponseDto containing details of all orders
     */
    public OrderResponseDto getOrderById(String orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        return new OrderResponseDto(order.getOrderId(), order.getCustomerId(), order.getOrderDate(),
                order.getTotalAmount(), order.getStatus(), orderItems);
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer whose orders are to be retrieved
     * @return List of OrderResponseDto containing details of the customer's orders
     */
    public List<OrderResponseDto> getOrdersByCustomerId(String customerId) {
        List<Orders> orders = orderRepository.findByCustomerId(customerId);
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        for (Orders order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getOrderId());
            OrderResponseDto orderResponseDto = new OrderResponseDto(order.getOrderId(), order.getCustomerId(),
                    order.getOrderDate(), order.getTotalAmount(), order.getStatus(), orderItems);
            orderResponseDtos.add(orderResponseDto);
        }

        return orderResponseDtos;
    }

    public void updateOrderStatus(String orderId, OrderStatusEnum status) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
        logger.info("Order status updated: Order ID = {}, New Status = {}", orderId, status);
    }

    private String generateOrderId() {
        // Logic to generate a unique order ID
        // This could be a UUID or any other unique identifier generation logic
        return "ord-" + UUID.randomUUID().toString().substring(0,8);
    }

    private String generateOrderItemId() {
        // Logic to generate a unique order ID
        // This could be a UUID or any other unique identifier generation logic
        return "item-" + UUID.randomUUID().toString().substring(0,8);
    }
    
}
