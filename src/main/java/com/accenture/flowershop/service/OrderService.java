package com.accenture.flowershop.service;

import com.accenture.flowershop.model.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> getAll();

    List<Order> getUserOrders(String userLogin);

    Order get(Long orderId);
}
