package com.accenture.flowershop.web.servlets;

import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.dao.OrderRepositoryImpl;
import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.dao.UserRepositoryImpl;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.Status;
import com.accenture.flowershop.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class OrderServlet extends HttpServlet {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderRepository = new OrderRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeForm = req.getParameter("typeForm");
        HttpSession session = req.getSession();
        Set<Order> listOrders = (Set<Order>) session.getAttribute("orders");

        if ("createForm".equals(typeForm)) {
            String sumValue = req.getParameter("createOrder");
            String userId = req.getParameter("userLogin");

            Order order = new Order(userId, Integer.parseInt(sumValue),
                    LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null, Status.CREATED);
            listOrders.add(orderRepository.save(order));
            resp.sendRedirect("mainpage");

        } else if ("payForm".equals(typeForm)) {
            Integer orderId = Integer.parseInt(req.getParameter("orderId"));
            String userLogin = req.getParameter("userLogin");
            int newBalance = Integer.parseInt(req.getParameter("newBalance"));

            orderRepository.update(orderId, null);
            Order payedOrder = orderRepository.get(orderId);
            payedOrder.setPayed(true);
            listOrders.remove(payedOrder);
            listOrders.add(payedOrder);

            userRepository.withdraw(userLogin, newBalance);
            User user = (User) session.getAttribute("user");
            user.setMoneyBalance(newBalance);
            resp.sendRedirect("mainpage");

        } else if ("closeForm".equals(typeForm)) {
            Integer orderId = Integer.parseInt(req.getParameter("orderId"));
            String closeDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
            orderRepository.update(orderId, closeDate);
            Set<Order> allOrders = (Set<Order>) session.getAttribute("allOrders");
            Order closedOrder = orderRepository.get(orderId);
            closedOrder.setClosed(true);
            allOrders.remove(closedOrder);
            allOrders.add(closedOrder);
            resp.sendRedirect("mainpage");
        }
    }
}
