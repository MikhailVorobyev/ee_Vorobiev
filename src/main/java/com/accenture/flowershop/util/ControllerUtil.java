package com.accenture.flowershop.util;

import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.service.OrderService;
import com.accenture.flowershop.to.OrderTo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ControllerUtil {

    private ControllerUtil() {
    }

    public static void fillOrderToList(HttpSession session, OrderService orderService) {
        List<Order> allOrders = orderService.getAll();
        Map<Long, OrderTo> orderToMap = AppUtil.convertToOrderTo(allOrders);
        session.setAttribute("allOrders", orderToMap);
    }
}
