package com.taskTracking.common.dto;

import javax.validation.constraints.NotBlank;

public class CreateTaskRequest {
    @NotBlank(message = "title is required")
    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
