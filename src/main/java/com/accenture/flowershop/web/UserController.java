package com.accenture.flowershop.web;

import com.accenture.flowershop.AppUtil;
import com.accenture.flowershop.dao.FlowerRepository;
import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.Status;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.UserService;
import com.accenture.flowershop.to.OrderTo;
import com.accenture.flowershop.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@Scope("session")
public class UserController {

    @Autowired
    private UserService userService;

    private final UserRepository userRepository;
    private final FlowerRepository flowerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserController(UserRepository userRepository, FlowerRepository flowerRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.flowerRepository = flowerRepository;
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrder(HttpSession session,
                              @RequestParam(value = "flowerData") String flowerData,
                              @RequestParam(value = "orderSum") String orderSum,
                              @RequestParam(value = "userLogin") String userLogin) {
        BigDecimal userBalance = ((UserTo) session.getAttribute("user")).getMoneyBalance();
        BigDecimal os = new BigDecimal(orderSum);
        if (os.compareTo(userBalance) > 0) {
            return "notEnoughMoney";
        }
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

        Order newOrder = new Order(new User(userLogin), new BigDecimal(orderSum),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null, Status.CREATED);

        Map<Long, OrderTo> orders = (Map<Long, OrderTo>) session.getAttribute("orders");
        Order savedOrder = orderRepository.save(newOrder);
        orders.put(savedOrder.getId(), AppUtil.createOrderTo(savedOrder));

        return "redirect:/userpage";
    }

    private void updateFlower(Flower updatedFlower) {
        flowerRepository.update(updatedFlower);
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    public String payOrder(HttpSession session,
                           @RequestParam(value = "orderId") String orderId,
                           @RequestParam(value = "userLogin") String userLogin,
                           @RequestParam(value = "orderSumToPay") String orderSum) {
        Long orId = Long.parseLong(orderId);

        Order payedOrder = orderRepository.get(orId);
        payedOrder.setStatus(Status.PAYED);
        orderRepository.save(payedOrder);

        Map<Long, OrderTo> orders = (Map<Long, OrderTo>) session.getAttribute("orders");
        orders.put(orId, AppUtil.createOrderTo(payedOrder));

        UserTo user = (UserTo) session.getAttribute("user");
        BigDecimal moneyBalance = user.getMoneyBalance();
        BigDecimal newBalance = moneyBalance.subtract(new BigDecimal(orderSum));
        userRepository.withdraw(userLogin, newBalance);

        user.setMoneyBalance(newBalance);

        return "redirect:userpage";
    }

    @RequestMapping(value = "closeOrder", method = RequestMethod.POST)
    public String closeOrder(HttpSession session,
                             @RequestParam(value = "orderId") String orderId) {
        Long orId = Long.parseLong(orderId);
        String closeDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        Order orderToPay = orderRepository.get(orId);
        orderToPay.setCloseDate(closeDate);
        orderRepository.save(orderToPay);

        Map<Long, OrderTo> orderToMap = (Map<Long, OrderTo>) session.getAttribute("allOrders");
        orderToMap.put(orId, AppUtil.createOrderTo(orderToPay));

        return "redirect:adminpage";
    }

    @GetMapping("/userpage")
    public String userpage(HttpSession session) {
        String userLogin = ((UserTo) session.getAttribute("user")).getLogin();
        Map<Long, OrderTo> orderToMap = AppUtil.convertToOrderTo(orderRepository.getUserOrders(userLogin));
        session.setAttribute("orders", orderToMap);
        return "userPage";
    }

    @GetMapping("/adminpage")
    public String adminpage(HttpSession session) {
        ControllerUtil.fillOrderToList(session, orderRepository);
        return "adminPage";
    }
}