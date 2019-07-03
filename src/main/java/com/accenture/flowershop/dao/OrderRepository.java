package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    List<Order> getAll();

    List<Order> getUserOrders(String userLogin);

    Order get(Long orderId);
}
