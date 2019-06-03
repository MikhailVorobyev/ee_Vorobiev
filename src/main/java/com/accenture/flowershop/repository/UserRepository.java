package com.accenture.flowershop.repository;

import com.accenture.flowershop.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();
}
