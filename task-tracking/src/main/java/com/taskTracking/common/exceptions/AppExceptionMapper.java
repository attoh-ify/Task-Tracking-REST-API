package com.taskTracking.common.exceptions;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
//A global JAX-RS exception handler for all exceptions extending AppException.
//Marked with @Provider, so JAX-RS automatically registers it
public class AppExceptionMapper implements ExceptionMapper<AppException> {
    @Override
    public Response toResponse(AppException ex) {
        JsonObject payload = Json.createObjectBuilder()
                .add("success", false)
                .add("message", ex.getMessage())
                .add("data", ex.getData() != null ? ex.getData() : JsonValue.NULL)
                .build();

        return Response.status(ex.getStatusCode())
                .entity(payload)
                .build();
    }
}