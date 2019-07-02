package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Order;

import java.util.Map;

public interface OrderRepository {

    Order save(Order order);

    Map<Integer, Order> getAll();

    Map<Integer, Order> getUserOrders(String userLogin);

    Order get(Integer orderId);
}
