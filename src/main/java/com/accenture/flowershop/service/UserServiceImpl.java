package com.accenture.flowershop.service;

import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.enums.SignInType;
import com.accenture.flowershop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static com.accenture.flowershop.util.ValidationUtil.checkNotFound;
import static com.accenture.flowershop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User get(String login) {
        return checkNotFoundWithId(userRepository.get(login), login);
    }

    @Override
    public void save(User user) {
        Assert.notNull(user, "user must be not null");
        userRepository.save(user);
    }

    @Override
    public boolean checkToken(String login, String password, SignInType signInType) {
        User user = userRepository.get(login);
        checkNotFound(user, user.getLogin());
        if (SignInType.REGISTRATION.equals(signInType)) {
            return login.equals(user.getLogin());
        } else if (SignInType.AUTHENTICATION.equals(signInType)) {
            return login.equals(user.getLogin()) && password.equals(user.getPassword());
        } else {
            return false;
        }
    }

    @Override
    public void withdraw(String login, BigDecimal newBalance) {
        userRepository.withdraw(login, newBalance);
    }
}
