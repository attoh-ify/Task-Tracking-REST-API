package com.taskTracking.common.exceptions;

import com.taskTracking.common.response.ApiResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        StringBuilder sb = new StringBuilder();
        exception.getConstraintViolations().forEach(v -> sb.append(v.getMessage()).append("; "));

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiResponse<>(false, sb.toString()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
