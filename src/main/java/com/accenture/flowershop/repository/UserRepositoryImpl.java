package com.accenture.flowershop.repository;

import com.accenture.flowershop.model.Role;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.util.Config;
import com.accenture.flowershop.util.ConnectionFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final ConnectionFactory connectionFactory;

    public UserRepositoryImpl() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<User>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM FLOWERSHOP.USER");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new User(rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("address"), rs.getString("phone_number"),
                        Double.parseDouble(rs.getString("money_balance")),
                        Integer.parseInt(rs.getString("discount")), Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e) {

        }
        return result;
    }
}
