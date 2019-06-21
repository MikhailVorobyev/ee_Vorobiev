package com.accenture.flowershop.web;

import com.accenture.flowershop.dao.*;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.Role;
import com.accenture.flowershop.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private FlowerRepository flowerRepository;
    private OrderRepository orderRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRepository = new UserRepositoryImpl();
        flowerRepository = new FlowerRepositoryImpl();
        orderRepository = new OrderRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("registration".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        String form = request.getParameter("form");

        boolean checkExistUser = userRepository.checkToken(login, password);
        if ("authorise".equals(form)) {
            if (checkExistUser) {
                HttpSession session = request.getSession();

                /*User*/
                User user = userRepository.get(login);
                session.setAttribute("user", user);

                /*Flowers*/
                List<Flower> list = flowerRepository.getAll();
                session.setAttribute("flowers", list);

                /*Empty order list*/
                Set<Order> orderList = new HashSet<>();
                session.setAttribute("orders", orderList);
                if ("admin123".equals(password)) {
                    Set<Order> allOrders = orderRepository.getAll();
                    session.setAttribute("allOrders", allOrders);
                    request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
            }

        } else if ("registration".equals(form)) {
            if (checkExistUser) {
                request.getRequestDispatcher("/WEB-INF/jsp/incorrectToken.jsp").forward(request, response);
            } else {
                userRepository.save(new User(login, password, name, surname, address, phone,
                        User.DEFAULT_USER_MONEY_BALANCE, 0, Role.ROLE_USER));
                response.sendRedirect("index.jsp");
            }
        }
    }
}
