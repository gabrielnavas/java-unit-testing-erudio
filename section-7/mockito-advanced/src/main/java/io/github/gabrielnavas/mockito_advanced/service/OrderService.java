package io.github.gabrielnavas.mockito_advanced.service;

import io.github.gabrielnavas.mockito_advanced.entity.Order;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderService {
    public Order createOrder(String productName, Long amount, UUID orderID) {
        Order order = new Order();

        order.setId(orderID == null ? UUID.randomUUID() : orderID);
        order.setCreationDate(LocalDateTime.now());
        order.setAmount(amount);
        order.setProductName(productName);

        return order;
    }
}
