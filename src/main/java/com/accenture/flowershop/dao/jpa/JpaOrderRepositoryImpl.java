package com.accenture.flowershop.dao.jpa;

import com.accenture.flowershop.dao.OrderRepository;
import com.accenture.flowershop.model.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Integer, Order> getAll() {
        List<Order> list = em.createNamedQuery(Order.GET_ALL_SORTED, Order.class).getResultList();
        return fillMap(list);
    }

    @Override
    public Map<Integer, Order> getUserOrders(String userLogin) {
        List<Order> list = em.createNamedQuery(Order.GET_CREATED_ORDERS, Order.class)
                .setParameter("userLogin", userLogin).getResultList();
        return fillMap(list);
    }

    @Override
    public Order get(Integer orderId) {
        return em.find(Order.class, orderId);
    }

    private Map<Integer, Order> fillMap(List<Order> list) {
        Map<Integer, Order> resultMap = new HashMap<>();
        for (Order order : list) {
            resultMap.put(order.getId(), order);
        }
        return resultMap;
    }
}
