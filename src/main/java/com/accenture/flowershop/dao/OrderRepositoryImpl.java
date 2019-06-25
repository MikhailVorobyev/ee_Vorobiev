package com.accenture.flowershop.dao;

import com.accenture.flowershop.exception.NotExistStorageException;
import com.accenture.flowershop.model.Order;
import com.accenture.flowershop.model.Status;
import com.accenture.flowershop.util.Config;
import com.accenture.flowershop.util.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final ConnectionFactory connectionFactory;

    public OrderRepositoryImpl() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    @Override
    public Order save(Order order) {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT NEXTVAL('FLOWERSHOP.ORDER_SEQ')");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            statement.close();

            PreparedStatement ps = conn.prepareStatement("" +
                    "INSERT INTO FLOWERSHOP.ORDERS (ID, USER_ID, SUM, CREATE_DATE, STATUS)" +
                    "VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, order.getId());
            ps.setString(2, order.getUserId());
            ps.setInt(3, order.getSum());
            ps.setString(4, order.getCreateDate());
            ps.setString(5, Status.CREATED.toString());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Set<Order> getAll() {
        Set<Order> resultList = new HashSet<>();
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FLOWERSHOP.ORDERS INNER JOIN FLOWERSHOP.USER " +
                    "ON USER_ID = LOGIN");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add(new Order(rs.getInt("ID"), rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"), rs.getString("ADDRESS"),
                        rs.getString("PHONE_NUMBER"), rs.getInt("SUM"),
                        rs.getString("CREATE_DATE"), rs.getString("CLOSE_DATE"),
                        Status.valueOf(rs.getString("STATUS"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public void update(Integer orderId, String closeDate) {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("" +
                    "UPDATE FLOWERSHOP.ORDERS SET STATUS = ?, CLOSE_DATE = ? WHERE ID = ?");
            ps.setString(1, Status.PAYED.toString());
            ps.setString(2, closeDate);
            ps.setInt(3, orderId);
            ps.execute();
        } catch (SQLException e) {
            throw new NotExistStorageException("Order with order id " + orderId + "not exists", e);
        }
    }

    @Override
    public Order get(Integer orderId) {
        Order result = null;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FLOWERSHOP.ORDERS WHERE ID = ?");
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Order(rs.getInt("ID"), rs.getString("USER_ID"), rs.getInt("SUM"),
                        rs.getString("CREATE_DATE"), null,
                        Status.valueOf(rs.getString("STATUS")));
            }
        } catch (SQLException e) {
            throw new NotExistStorageException("Order with order id " + orderId + "not exists", e);
        }
        return result;
    }
}
