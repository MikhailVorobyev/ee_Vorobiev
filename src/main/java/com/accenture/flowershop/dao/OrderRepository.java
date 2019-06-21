package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Order;

import java.util.Set;

public interface OrderRepository {

    Order save(Order order);

    Set<Order> getAll();

    void update(Integer orderId, String closeDate);

    Order get(Integer orderId);
}
