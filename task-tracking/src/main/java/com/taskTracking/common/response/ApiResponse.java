package com.taskTracking.common.response;

import javax.json.bind.annotation.JsonbProperty;

public class ApiResponse<T> {
    @JsonbProperty("success")
    private boolean success;

    @JsonbProperty("message")
    private String message;

    @JsonbProperty("data")
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
