package com.accenture.flowershop.service;

import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User get(String login) {
        return userRepository.get(login);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkToken(String login, String password) {
        return userRepository.checkToken(login, password) != null;
    }

    @Override
    public void withdraw(String login, BigDecimal newBalance) {
        userRepository.withdraw(login, newBalance);
    }
}
