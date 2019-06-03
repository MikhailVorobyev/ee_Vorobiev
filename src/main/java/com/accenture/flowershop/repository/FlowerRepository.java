package com.accenture.flowershop.repository;

import com.accenture.flowershop.model.Flower;

import java.util.List;

public interface FlowerRepository {

    List<Flower> getAll();
}
