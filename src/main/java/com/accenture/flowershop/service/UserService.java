package com.accenture.flowershop.service;

import com.accenture.flowershop.model.User;

public interface UserService {

    User get(String login);

    void save(User user);

    boolean checkToken(String login, String password);

    void withdraw(String login, Integer newBalance);
}
