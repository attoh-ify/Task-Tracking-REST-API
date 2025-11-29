package com.taskTracking.users;

import com.taskTracking.common.dto.LoginRequest;
import com.taskTracking.common.dto.LoginResponse;
import com.taskTracking.common.dto.UserResponse;
import com.taskTracking.common.response.ApiResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    public Response create(@Valid LoginRequest request) {
        LoginResponse loginResponse = userService.login(request);
        return Response.ok(new ApiResponse<>(true, "User login successfully", loginResponse)).build();
    }
}
