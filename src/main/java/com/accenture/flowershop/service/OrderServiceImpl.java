package com.accenture.flowershop.service;

import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.accenture.flowershop.util.ValidationUtil.checkNotFound;
import static com.accenture.flowershop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        Assert.notNull(order, "meal must not be null");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public List<Order> getUserOrders(String userLogin) {
        return checkNotFound(orderRepository.getUserOrders(userLogin), userLogin);
    }

    @Override
    public Order get(Long orderId) {
        return checkNotFoundWithId(orderRepository.get(orderId), orderId);
    }
}
