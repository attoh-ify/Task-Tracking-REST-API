package com.taskTracking.listeners;

import com.taskTracking.events.TaskCreatedEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.time.LocalDateTime;

@ApplicationScoped
public class AuditListener {
    public void onTaskCreated(@Observes TaskCreatedEvent event) {
        System.out.println(
                "[AUDIT] " + LocalDateTime.now() +
                " | Task Created: " + event.getTaskId() +
                " | Owner: " + event.getOwnerId() +
                " | Title: " + event.getTitle()
        );
    }
}
