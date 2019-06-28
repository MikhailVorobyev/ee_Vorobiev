package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Flower;

import java.util.List;

public interface FlowerRepository {

    Flower get(String name);

    List<Flower> getAll();

    void update(Flower flower);
}
