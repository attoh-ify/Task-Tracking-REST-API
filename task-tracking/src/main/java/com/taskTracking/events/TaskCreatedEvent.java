package com.taskTracking.events;

import com.taskTracking.tasks.Task;

public class TaskCreatedEvent {
    private final String taskId;
    private final String ownerId;
    private final String title;

    public TaskCreatedEvent(Task task) {
        this.taskId = task.getId();
        this.ownerId = task.getUser().getId();
        this.title = task.getTitle();
    }

    public String getTaskId() {
        return taskId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }
}
