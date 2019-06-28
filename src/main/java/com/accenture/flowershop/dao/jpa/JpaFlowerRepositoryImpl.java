package com.accenture.flowershop.dao.jpa;

import com.accenture.flowershop.dao.FlowerRepository;
import com.accenture.flowershop.model.Flower;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaFlowerRepositoryImpl implements FlowerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Flower get(String name) {
        List<Flower> flowers = em.createNamedQuery(Flower.GET, Flower.class)
                .setParameter("name", name)
                .getResultList();
        return DataAccessUtils.singleResult(flowers);
    }

    @Override
    public List<Flower> getAll() {
        return em.createNamedQuery(Flower.GET_ALL, Flower.class).getResultList();
    }

    @Override
    @Transactional
    public void update(Flower flower) {
        em.merge(flower);
    }
}
