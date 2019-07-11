package com.accenture.flowershop.util;

import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.enums.Status;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.to.OrderTo;
import com.accenture.flowershop.to.UserTo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppUtil {
    private AppUtil() {
    }

    public static Map<Long, OrderTo> convertToOrderTo(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.toMap(Order::getId, AppUtil::createOrderTo));
    }

    public static OrderTo createOrderTo(Order order) {
        return createOrderTo(order, Status.PAYED == order.getStatus(), order.getCloseDate() != null);
    }

    private static OrderTo createOrderTo(Order order, boolean payed, boolean closed) {
        return new OrderTo(order.getId(), createUserTo(order.getUser()), order.getSum(), order.getCreateDate(),
                order.getCloseDate(), order.getStatus(), payed, closed);
    }

    public static UserTo createUserTo(User user) {
        return new UserTo(user.getLogin(), user.getFirstName(), user.getLastName(), user.getAddress(),
                user.getPhoneNumber(), user.getMoneyBalance(), user.getDiscount());
    }
}
