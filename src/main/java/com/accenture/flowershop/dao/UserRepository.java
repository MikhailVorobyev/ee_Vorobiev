package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.User;

public interface UserRepository {
    User get(String login);

    void save(User user);

    User checkToken(String login, String password);

    void withdraw(String login, Integer newBalance);
}
