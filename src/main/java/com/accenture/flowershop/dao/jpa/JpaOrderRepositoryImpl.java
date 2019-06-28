package com.accenture.flowershop.dao.jpa;

import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaOrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            order = em.merge(order);
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.GET_ALL_SORTED, Order.class).getResultList();
    }

    @Override
    public Order get(Integer orderId) {
        return em.find(Order.class, orderId);
    }
}
