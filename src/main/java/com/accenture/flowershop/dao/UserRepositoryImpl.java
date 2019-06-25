package com.accenture.flowershop.dao;

import com.accenture.flowershop.exception.ExistStorageException;
import com.accenture.flowershop.exception.NotExistStorageException;
import com.accenture.flowershop.model.Role;
import com.accenture.flowershop.model.User;
import com.accenture.flowershop.util.Config;
import com.accenture.flowershop.util.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final ConnectionFactory connectionFactory;

    public UserRepositoryImpl() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    public User get(String login) {
        User result = null;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FLOWERSHOP.USER WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new User(rs.getString("login"),
                        rs.getDouble("money_Balance"), rs.getInt("discount"));
            }
        } catch (SQLException e) {
            throw new NotExistStorageException("User with login " + login.toUpperCase() + " not exists", e);
        }
        return result;
    }

    public void save(User user) {

        try (Connection conn = connectionFactory.getConnection()) {
            try {
                PreparedStatement psUser = conn.prepareStatement("" +
                        "INSERT INTO FLOWERSHOP.USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement psRole = conn.prepareStatement("INSERT INTO FLOWERSHOP.ROLE VALUES ( ?, ? )");
                conn.setAutoCommit(false);
                psUser.setString(1, user.getLogin());
                psUser.setString(2, user.getFirstName());
                psUser.setString(3, user.getLastName());
                psUser.setString(4, user.getPassword());
                psUser.setString(5, user.getAddress());
                psUser.setString(6, user.getPhoneNumber());
                psUser.setInt(7, User.DEFAULT_USER_MONEY_BALANCE);
                psUser.setInt(8, user.getDiscount());

                psRole.setString(1, user.getLogin());
                psRole.setString(2, Role.ROLE_USER.toString());

                psUser.execute();
                psRole.execute();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public boolean checkToken(String login, String password) {
        boolean result = false;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("" +
                    "SELECT login, password FROM FLOWERSHOP.USER WHERE login = ? AND password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            result = ps.executeQuery().next();
        } catch (SQLException e) {
            throw new ExistStorageException("User already exists", e);
        }
        return result;
    }

    @Override
    public void withdraw(String userLogin, Integer newBalance) {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("" +
                    "UPDATE FLOWERSHOP.USER SET MONEY_BALANCE = ? WHERE LOGIN = ?");
            ps.setInt(1, newBalance);
            ps.setString(2, userLogin);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
