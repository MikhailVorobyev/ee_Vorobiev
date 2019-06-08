package com.accenture.flowershop.repository;

import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.util.Config;
import com.accenture.flowershop.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerRepositoryImpl implements FlowerRepository {
    private final ConnectionFactory connectionFactory;

    public FlowerRepositoryImpl() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    @Override
    public List<Flower> getAll() {
        List<Flower> result = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM FLOWERSHOP.FLOWER");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Flower(rs.getString("name"),
                        Integer.parseInt(rs.getString("price")),
                        Integer.parseInt(rs.getString("quantity"))));
            }
        } catch (SQLException e) {

        }
        return result;
    }
}
