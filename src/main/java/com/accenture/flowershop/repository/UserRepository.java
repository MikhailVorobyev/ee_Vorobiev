package com.accenture.flowershop.repository;

import com.accenture.flowershop.model.User;

public interface UserRepository {
    User get(String login);

    void save(User user);

    boolean checkToken(String login, String password);
}
