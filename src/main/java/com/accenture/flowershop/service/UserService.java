package com.accenture.flowershop.service;

import com.accenture.flowershop.enums.SignInType;
import com.accenture.flowershop.model.User;

import java.math.BigDecimal;

public interface UserService {

    User get(String login);

    void save(User user);

    boolean checkToken(String login, String password, SignInType signInType);

    void withdraw(String login, BigDecimal newBalance);
}
