package com.accenture.flowershop.web;

import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.dao.OrderRepositoryImpl;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class MainPageServlet extends HttpServlet {
    OrderRepository orderRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderRepository = new OrderRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String userLogin = ((User) httpSession.getAttribute("user")).getLogin();
        if ("admin".equals(userLogin)) {
            Set<Order> allOrders = orderRepository.getAll();
            for (Order order : allOrders) {
                if (order.getCloseDate() != null) {
                    order.setClosed(true);
                }
            }
            httpSession.setAttribute("allOrders", allOrders);
            request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp").forward(request, response);
        }
    }
}
