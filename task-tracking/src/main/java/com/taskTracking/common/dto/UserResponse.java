package com.taskTracking.common.dto;

import com.taskTracking.common.Enums;
import com.taskTracking.users.User;

public class UserResponse {
    private String userId;
    private String username;
    private Enums.ROLES role;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Enums.ROLES getRole() {
        return role;
    }
}
