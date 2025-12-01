package com.taskTracking.users;

import com.taskTracking.common.dto.CreateUserRequest;
import com.taskTracking.common.dto.UserResponse;
import com.taskTracking.common.response.ApiResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    private UserService userService;

    @POST
    @Path("/")
    public Response create(@Valid CreateUserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return Response.ok(new ApiResponse<>(true, "User created successfully", userResponse)).build();
    }

    @POST
    @Path("/admin")
    public Response createAdmin(@Valid CreateUserRequest request) {
        UserResponse userResponse = userService.createAdmin(request);
        return Response.ok(new ApiResponse<>(true, "Admin created successfully", userResponse)).build();
    }
}
