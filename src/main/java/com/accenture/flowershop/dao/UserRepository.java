package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.User;

import java.util.List;

public interface UserRepository {
    User get(String login);

    void save(User user);

    boolean checkToken(String login, String password);

    void withdraw(String login, Integer newBalance);
}
