package com.accenture.flowershop.web;

import com.accenture.flowershop.util.AppUtil;
import com.accenture.flowershop.enums.Status;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.FlowerService;
import com.accenture.flowershop.service.OrderService;
import com.accenture.flowershop.service.UserService;
import com.accenture.flowershop.to.OrderTo;
import com.accenture.flowershop.to.UserTo;
import com.accenture.flowershop.util.ControllerUtil;
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
import java.util.Map;

@Controller
@Scope("session")
public class UserController {

    private final UserService userService;
    private final FlowerService flowerService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, FlowerService flowerService, OrderService orderService) {
        this.userService = userService;
        this.flowerService = flowerService;
        this.orderService = orderService;
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

            Flower updatedFlower = flowerService.get(flowerName);
            updatedFlower.setQuantity(updatedFlower.getQuantity() - flowerCount);

            updateFlower(updatedFlower);

            session.removeAttribute("flowers");
            session.setAttribute("flowers", flowerService.getAll());
        }

        Order newOrder = new Order(new User(userLogin), new BigDecimal(orderSum),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), null, Status.CREATED);

        @SuppressWarnings("unchecked")
        Map<Long, OrderTo> orders = (Map<Long, OrderTo>) session.getAttribute("orders");
        Order savedOrder = orderService.save(newOrder);
        orders.put(savedOrder.getId(), AppUtil.createOrderTo(savedOrder));

        return "redirect:/userpage";
    }

    private void updateFlower(Flower updatedFlower) {
        flowerService.update(updatedFlower);
    }

    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    public String payOrder(HttpSession session,
                           @RequestParam(value = "orderId") String orderId,
                           @RequestParam(value = "userLogin") String userLogin,
                           @RequestParam(value = "orderSumToPay") String orderSum) {
        Long orId = Long.parseLong(orderId);

        Order payedOrder = orderService.get(orId);
        payedOrder.setStatus(Status.PAYED);
        orderService.save(payedOrder);

        @SuppressWarnings("unchecked")
        Map<Long, OrderTo> orders = (Map<Long, OrderTo>) session.getAttribute("orders");
        orders.put(orId, AppUtil.createOrderTo(payedOrder));

        UserTo user = (UserTo) session.getAttribute("user");
        BigDecimal moneyBalance = user.getMoneyBalance();
        BigDecimal newBalance = moneyBalance.subtract(new BigDecimal(orderSum));
        userService.withdraw(userLogin, newBalance);

        user.setMoneyBalance(newBalance);

        return "redirect:userpage";
    }

    @RequestMapping(value = "closeOrder", method = RequestMethod.POST)
    public String closeOrder(HttpSession session,
                             @RequestParam(value = "orderId") String orderId) {
        Long orId = Long.parseLong(orderId);
        String closeDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        Order orderToPay = orderService.get(orId);
        orderToPay.setCloseDate(closeDate);
        orderService.save(orderToPay);

        @SuppressWarnings("unchecked")
        Map<Long, OrderTo> orderToMap = (Map<Long, OrderTo>) session.getAttribute("allOrders");
        orderToMap.put(orId, AppUtil.createOrderTo(orderToPay));

        return "redirect:adminpage";
    }

    @GetMapping("/userpage")
    public String userpage(HttpSession session) {
        String userLogin = ((UserTo) session.getAttribute("user")).getLogin();
        Map<Long, OrderTo> orderToMap = AppUtil.convertToOrderTo(orderService.getUserOrders(userLogin));
        session.setAttribute("orders", orderToMap);
        return "userPage";
    }

    @GetMapping("/adminpage")
    public String adminpage(HttpSession session) {
        ControllerUtil.fillOrderToList(session, orderService);
        return "adminPage";
    }
}