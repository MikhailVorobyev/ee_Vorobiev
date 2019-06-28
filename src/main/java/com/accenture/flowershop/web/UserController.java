package com.accenture.flowershop.web;

import com.accenture.flowershop.dao.FlowerRepository;
import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Scope("session")
public class UserController {

    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserController(UserRepository userRepository, FlowerRepository flowerRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.flowerRepository = flowerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/authorisation", method = RequestMethod.POST)
    public String authorisation(HttpSession session,
                                      @RequestParam(value = "login") String login,
                                      @RequestParam(value = "password") String password) {

        if (checkUser(login, password)) {

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
                List<Order> allOrders = orderRepository.getAll();
                session.setAttribute("allOrders", allOrders);
                return "adminPage";
            } else {
                return "userPage";
            }

        } else {
            return "registration";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "login") String login,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "address") String address,
                               @RequestParam(value = "phone") String phone) {
        if (checkUser(login, password)) {
            return "incorrectToken";
        } else {
            userRepository.save(new User(login, password, name, surname, address, phone,
                    User.DEFAULT_USER_MONEY_BALANCE, 0, Role.ROLE_USER.toString()));
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrder(HttpSession session,
                              @RequestParam(value = "flowerData") String flowerData,
                              @RequestParam(value = "orderSum") String orderSum,
                              @RequestParam(value = "userLogin") String userLogin) {
        String[] flowerNameAndCost = flowerData.split(";");
        for (String flowerPair : flowerNameAndCost) {
            String[] pair = flowerPair.split("-");
            String flowerName = pair[0];
            int flowerCount = Integer.parseInt(pair[1]);

            Flower updatedFlower = flowerRepository.get(flowerName);
            updatedFlower.setQuantity(updatedFlower.getQuantity() - flowerCount);

            updateFlower(updatedFlower);

            session.removeAttribute("flowers");
            session.setAttribute("flowers", flowerRepository.getAll());
        }

        Order order = new Order(new User(userLogin), Integer.parseInt(orderSum),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null, Status.CREATED);
        Set<Order> orders = (Set<Order>) session.getAttribute("orders");
        orders.add(orderRepository.save(order));

        return "redirect:/mainpage";
    }

    private void updateFlower(Flower updatedFlower) {
        flowerRepository.update(updatedFlower);
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    public String payOrder(HttpSession session,
                           @RequestParam(value = "orderId") String orderId,
                           @RequestParam(value = "userLogin") String userLogin,
                           @RequestParam(value = "newBalance") String newBalance) {
        Integer orId = Integer.parseInt(orderId);

        Order payedOrder = orderRepository.get(orId);
        payedOrder.setStatus(Status.PAYED);
        orderRepository.save(payedOrder);

        Set<Order> orders = (Set<Order>) session.getAttribute("orders");
        payedOrder.setPayed(true);
        orders.remove(payedOrder);
        orders.add(payedOrder);

        int nBalance = Integer.parseInt(newBalance);
        userRepository.withdraw(userLogin, nBalance);
        User user = (User) session.getAttribute("user");
        user.setMoneyBalance(nBalance);


        return "redirect:mainpage";
    }

    @RequestMapping(value = "closeOrder", method = RequestMethod.POST)
    public String closeOrder(HttpSession session,
                             @RequestParam(value = "orderId") String orderId) {
        Integer orId = Integer.parseInt(orderId);
        String closeDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        Order newOrder = orderRepository.get(orId);
        newOrder.setCloseDate(closeDate);
        Order closedOrder = orderRepository.save(newOrder);

        List<Order> allOrders = (List<Order>) session.getAttribute("allOrders");

        closedOrder.setClosed(true);
        allOrders.remove(closedOrder);
        allOrders.add(closedOrder);
        return "redirect:mainpage";
    }

    @GetMapping("/mainpage")
    public String mainpage(HttpSession session) {

        String userRole = ((User) session.getAttribute("user")).getRole();
        if (Role.ROLE_ADMIN.toString().equals(userRole)) {
            List<Order> allOrders = orderRepository.getAll();
            for (Order order : allOrders) {
                if (order.getCloseDate() != null) {
                    order.setClosed(true);
                }
            }
            session.setAttribute("allOrders", allOrders);
            return "adminPage";
        } else {
            return "userPage";
        }
    }

    private boolean checkUser(String login, String password) {
        return userRepository.checkToken(login, password);
    }
}