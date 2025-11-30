package com.taskTracking.users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "TASK-TRACKING-Unit")
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class
        );
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public User findById(String userId) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.id = :userId", User.class
        );
        query.setParameter("userId", userId);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
