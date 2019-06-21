package com.accenture.flowershop.dao;

import com.accenture.flowershop.model.Flower;

import java.util.List;

public interface FlowerRepository {

    List<Flower> getAll();
}
