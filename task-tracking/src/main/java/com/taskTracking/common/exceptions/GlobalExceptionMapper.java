//package com.taskTracking.common.exceptions;
//
//import com.taskTracking.common.response.ApiResponse;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
//    @Override
//    public Response toResponse(Throwable exception) {
//        int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
//        String message = exception.getMessage() != null ? exception.getMessage() : "Internal server error";
//
//        return Response.status(status)
//            .entity(new ApiResponse<>(false, message))
//            .type(MediaType.APPLICATION_JSON)
//            .build();
//    }
//}
