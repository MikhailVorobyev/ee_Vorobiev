package com.accenture.flowershop.dao.jpa;

import com.accenture.flowershop.dao.UserRepository;
import com.accenture.flowershop.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User get(String login) {
        return em.find(User.class, login);
    }

    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User checkToken(String login, String password) {
        List<User> list =  em.createNamedQuery(User.CHECK_TOKEN, User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getResultList();
        return DataAccessUtils.singleResult(list);
    }

    @Override
    @Transactional
    public void withdraw(String login, BigDecimal newBalance) {
        em.createNamedQuery(User.WITHDRAW)
                .setParameter("login", login)
                .setParameter("newBalance", newBalance)
                .executeUpdate();
    }
}
