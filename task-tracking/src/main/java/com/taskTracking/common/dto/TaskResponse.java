package com.taskTracking.common.dto;

import com.taskTracking.tasks.Task;

import java.time.LocalDateTime;

public class TaskResponse {
    private String taskId;
    private String title;
    private String description;
    private String ownerId;
    private LocalDateTime createdAt;

    public TaskResponse(Task task) {
        this.taskId = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.ownerId = task.getUser().getId();
        this.createdAt = task.getCreatedAt();
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
