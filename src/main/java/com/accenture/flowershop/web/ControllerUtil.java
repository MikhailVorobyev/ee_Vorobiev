package com.accenture.flowershop.web;

import com.accenture.flowershop.AppUtil;
import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.service.UserService;
import com.accenture.flowershop.to.OrderTo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ControllerUtil {

    static boolean checkUser(UserService userService, String login, String password) {
        return userService.checkToken(login, password);
    }

    static void fillOrderToList(HttpSession session, OrderRepository orderRepository) {
        List<Order> allOrders = orderRepository.getAll();
        Map<Long, OrderTo> orderToList = AppUtil.convertToOrderTo(allOrders);
        session.setAttribute("allOrders", orderToList);
    }
}
