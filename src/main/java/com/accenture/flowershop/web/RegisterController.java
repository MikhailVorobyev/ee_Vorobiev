package com.accenture.flowershop.web;

import com.accenture.flowershop.model.Role;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "login") String login,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "surname") String surname,
                               @RequestParam(value = "address") String address,
                               @RequestParam(value = "phone") String phone) {
        if (ControllerUtil.checkUser(userService, login, password)) {
            return "incorrectToken";
        } else {
            userService.save(new User(login, password, name, surname, address, phone,
                    User.DEFAULT_USER_MONEY_BALANCE, 0, Role.ROLE_USER.toString()));
            return "redirect:/";
        }
    }
}
