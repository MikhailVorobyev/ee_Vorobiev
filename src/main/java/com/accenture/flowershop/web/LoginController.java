package com.accenture.flowershop.web;

import com.accenture.flowershop.util.AppUtil;
import com.accenture.flowershop.enums.SignInType;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.FlowerService;
import com.accenture.flowershop.service.OrderService;
import com.accenture.flowershop.service.UserService;
import com.accenture.flowershop.to.OrderTo;
import com.accenture.flowershop.util.ControllerUtil;
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
    private OrderService orderService;
    private FlowerService flowerService;

    @Autowired
    public LoginController(UserService userService, OrderService orderService, FlowerService flowerService) {
        this.userService = userService;
        this.orderService = orderService;
        this.flowerService = flowerService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/authorisation", method = RequestMethod.POST)
    public String authorisation(HttpSession session,
                                @RequestParam(value = "login") String login,
                                @RequestParam(value = "password") String password) {

        if (!userService.checkToken(login, password, SignInType.AUTHENTICATION)) {
            return "authenticationError";

        } else {
            //User
            User user = userService.get(login);
            session.setAttribute("user", AppUtil.createUserTo(user));

            if ("admin".equals(login)) {
                ControllerUtil.fillOrderToList(session, orderService);
                return "redirect:/adminpage";

            } else {
                //Flowers
                List<Flower> list = flowerService.getAll();
                session.setAttribute("flowers", list);

                //Orders
                List<Order> orderList = orderService.getUserOrders(login);
                Map<Long, OrderTo> orderToList = AppUtil.convertToOrderTo(orderList);
                session.setAttribute("orders", orderToList);

                return "redirect:/userpage";
            }
        }
    }
}
