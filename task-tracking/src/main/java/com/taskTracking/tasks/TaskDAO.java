package com.taskTracking.tasks;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TaskDAO {
    @PersistenceContext(unitName = "TASK-TRACKING-Unit")
    EntityManager em;

    public void save(Task task) {
        em.persist(task);
    }

    public void update(Task task) {
        em.merge(task);
    }

    public void delete(Task task) {
        em.remove(em.contains(task) ? task : em.merge(task));
    }

    public Task findById(String taskId) {
        TypedQuery<Task> query = em.createQuery(
                "SELECT t FROM Task t WHERE t.id = :taskId", Task.class
        );
        query.setParameter("taskId", taskId);
        List<Task> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Task> findByOwnerId(String userId) {
        TypedQuery<Task> query = em.createQuery(
                "SELECT t FROM Task t WHERE t.user.id = :userId", Task.class
        );
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Task> findAll() {
        TypedQuery<Task> query = em.createQuery(
                "SELECT t FROM Task t", Task.class
        );
        return query.getResultList();
    }
}
