package com.accenture.flowershop.web;

import com.accenture.flowershop.AppUtil;
import com.accenture.flowershop.dao.FlowerRepository;
import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.UserService;
import com.accenture.flowershop.to.OrderTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
public class LoginController {

    private UserService userService;
    private OrderRepository orderRepository;
    private FlowerRepository flowerRepository;

    @Autowired
    public LoginController(UserService userService, OrderRepository orderRepository, FlowerRepository flowerRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.flowerRepository = flowerRepository;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/authorisation", method = RequestMethod.POST)
    public String authorisation(HttpSession session,
                                @RequestParam(value = "login") String login,
                                @RequestParam(value = "password") String password) {

        if (ControllerUtil.checkUser(userService, login, password)) {

            //User
            User user = userService.get(login);
            session.setAttribute("user", AppUtil.createUserTo(user));

            if ("admin".equals(login)) {
                ControllerUtil.fillOrderToList(session, orderRepository);
                return "redirect:/adminpage";

            } else {
                //Flowers
                List<Flower> list = flowerRepository.getAll();
                session.setAttribute("flowers", list);

                //Orders
                List<Order> orderList = orderRepository.getUserOrders(login);
                Map<Long, OrderTo> orderToList = AppUtil.convertToOrderTo(orderList);
                session.setAttribute("orders", orderToList);

                return "redirect:/userpage";
            }
        } else {
            return "authenticationError";
        }
    }
}
