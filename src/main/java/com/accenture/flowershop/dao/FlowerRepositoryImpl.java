package com.accenture.flowershop.dao;

import com.accenture.flowershop.exception.NotExistStorageException;
import com.accenture.flowershop.model.Flower;
import com.accenture.flowershop.util.Config;
import com.accenture.flowershop.util.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerRepositoryImpl implements FlowerRepository {
    private final ConnectionFactory connectionFactory;

    public FlowerRepositoryImpl() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    @Override
    public Flower get(String name) {
        Flower flower = null;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM FLOWERSHOP.FLOWER WHERE NAME = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                flower = new Flower(rs.getInt("ID"), rs.getString("NAME"),
                        rs.getInt("PRICE"), rs.getInt("QUANTITY"));
            }
        } catch (SQLException e) {
            throw new NotExistStorageException("flower with name " + name + "not exists", e);
        }
        return flower;
    }

    @Override
    public List<Flower> getAll() {
        List<Flower> result = new ArrayList<>();
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM FLOWERSHOP.FLOWER");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Flower(rs.getInt("id"), rs.getString("name"),
                        Integer.parseInt(rs.getString("price")),
                        Integer.parseInt(rs.getString("quantity"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Integer flowerId, Integer quantity) {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE FLOWERSHOP.FLOWER SET QUANTITY = ? WHERE ID = ?");
            ps.setInt(1, quantity);
            ps.setInt(2, flowerId);
            ps.execute();
        } catch (SQLException e) {
            throw new NotExistStorageException("flower with name " + flowerId + "not exists", e);
        }
    }
}
