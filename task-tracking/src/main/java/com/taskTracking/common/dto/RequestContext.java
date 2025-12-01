package com.taskTracking.common.dto;

import com.taskTracking.common.Enums;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestContext {
    private String userId;
    private Enums.ROLES role;

    public RequestContext() {}

    public String getUserId() {
        return userId;
    }

    public Enums.ROLES getRole() {
        return role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(Enums.ROLES role) {
        this.role = role;
    }
}