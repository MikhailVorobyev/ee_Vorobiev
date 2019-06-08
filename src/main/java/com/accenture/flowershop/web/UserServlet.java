package com.accenture.flowershop.web;

import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Role;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.repository.FlowerRepository;
import com.accenture.flowershop.repository.FlowerRepositoryImpl;
import com.accenture.flowershop.repository.UserRepository;
import com.accenture.flowershop.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private FlowerRepository flowerRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        userRepository = new UserRepositoryImpl();
        flowerRepository = new FlowerRepositoryImpl();
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
                User user = userRepository.get(login);
                request.setAttribute("user", user);
                List<Flower> list = flowerRepository.getAll();
                request.setAttribute("flowers", list);
                request.getRequestDispatcher("/WEB-INF/jsp/flowers.jsp").forward(request, response);
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
