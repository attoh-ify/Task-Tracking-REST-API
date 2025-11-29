package com.taskTracking.users;

import com.taskTracking.common.Enums;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {
    @PersistenceContext(unitName = "TASK-TRACKING-Unit")
    EntityManager em;

    public void test() {
        var user = new User(
                "tobe",
                "password",
                Enums.ROLES.USER
        );
        em.persist(user);
    }
}
