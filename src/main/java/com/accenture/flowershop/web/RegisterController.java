package com.accenture.flowershop.web;

import com.accenture.flowershop.enums.SignInType;
import com.accenture.flowershop.marshallingservice.UserMarshallingService;
import com.accenture.flowershop.enums.Role;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class RegisterController {
    private static final String XML_FILE_NAME = System.getenv("FLOWERSHOP_HOME") + "\\newUser.xml";
    private UserService userService;

    private UserMarshallingService userMarshallingService;

    @Autowired
    public RegisterController(UserService userService, UserMarshallingService userMarshallingService) {
        this.userService = userService;
        this.userMarshallingService = userMarshallingService;
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
        if (userService.checkToken(login, password, SignInType.REGISTRATION)) {

            return "incorrectToken";
        } else {
            User newUser = new User(login, password, name, surname, address, phone,
                    User.DEFAULT_USER_MONEY_BALANCE, 5, Role.ROLE_USER.toString());
            try {
                userMarshallingService.convertFromObjectToXML(newUser, XML_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            userService.save(newUser);
            return "redirect:/";
        }
    }
}
