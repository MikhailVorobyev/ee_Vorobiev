package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.User;

import java.math.BigDecimal;

public interface UserRepository {
    User get(String login);

    void save(User user);

    User checkToken(String login);

    void withdraw(String login, BigDecimal newBalance);
}
