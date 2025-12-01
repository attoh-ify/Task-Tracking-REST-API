package com.taskTracking.listeners;

import com.taskTracking.events.TaskCreatedEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class AuditListener {
    public void onTaskCreated(@Observes TaskCreatedEvent event) {
        System.out.println("[AUDIT LISTENER} Task created -> " + event.getTaskId());
    }
}
