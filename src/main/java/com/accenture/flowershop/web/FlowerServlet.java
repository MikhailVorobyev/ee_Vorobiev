package com.accenture.flowershop.web;

import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.repository.FlowerRepository;
import com.accenture.flowershop.repository.FlowerRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FlowerServlet extends HttpServlet {
    private FlowerRepository flowerRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        flowerRepository = new FlowerRepositoryImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");
        List<Flower> list = flowerRepository.getAll();
        request.setAttribute("flowers", list);
        request.getRequestDispatcher("/WEB-INF/jsp/flowers.jsp").forward(request, response);
    }
}
