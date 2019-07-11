package com.accenture.flowershop.service;

import com.accenture.flowershop.dao.FlowerRepository;
import com.accenture.flowershop.model.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.accenture.flowershop.util.ValidationUtil.checkNotFoundWithId;
@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public Flower get(String name) {
        return checkNotFoundWithId(flowerRepository.get(name), name);
    }

    @Override
    public List<Flower> getAll() {
        return flowerRepository.getAll();
    }

    @Override
    public void update(Flower flower) {
        Assert.notNull(flower, "user must not be null");
        checkNotFoundWithId(flowerRepository.update(flower), flower.getId());
    }
}
