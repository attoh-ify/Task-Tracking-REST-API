package com.taskTracking.listeners;

import com.taskTracking.events.TaskCreatedEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

@ApplicationScoped
public class NotificationListener {
    public void onTaskCreated(@ObservesAsync TaskCreatedEvent event) {
        System.out.println("[NOTIFICATION LISTENER] Task created for user: " + event.getOwnerId());
    }
}
