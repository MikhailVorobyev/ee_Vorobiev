package com.accenture.flowershop.service;

import com.accenture.flowershop.model.Flower;

import java.util.List;

public interface FlowerService {
    Flower get(String name);

    List<Flower> getAll();

    void update(Flower flower);
}
