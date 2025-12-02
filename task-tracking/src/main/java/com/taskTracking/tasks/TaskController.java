package com.taskTracking.tasks;

import com.taskTracking.common.Enums;
import com.taskTracking.common.dto.CreateTaskRequest;
import com.taskTracking.common.dto.RequestContext;
import com.taskTracking.common.dto.TaskResponse;
import com.taskTracking.common.response.ApiResponse;
import com.taskTracking.security.RolesAllowed;
import com.taskTracking.security.Secured;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaskController {
    @Inject
    private TaskService taskService;

    @Inject
    private RequestContext requestContext;

    @POST
    @Secured
    @RolesAllowed({Enums.ROLES.USER})
    public Response create(@Valid CreateTaskRequest request) {
        String userId = requestContext.getUserId();
        TaskResponse taskResponse = taskService.createTask(request, userId);
        return Response.ok(new ApiResponse<>(true, "Task created successfully", taskResponse)).build();
    }

    @GET
    @Secured
    @RolesAllowed({Enums.ROLES.USER, Enums.ROLES.ADMIN})
    public Response getTasks() {
        String userId = requestContext.getUserId();
        List<TaskResponse> taskResponses = taskService.getTaskByUser(userId);
        return Response.ok(new ApiResponse<>(true, "Tasks retrieved", taskResponses)).build();
    }

    @GET
    @Secured
    @Path("/{taskId}")
    @RolesAllowed({Enums.ROLES.USER, Enums.ROLES.ADMIN})
    public Response getTaskById(@PathParam("taskId") String taskId) {
        TaskResponse taskResponse = taskService.getTaskById(taskId);
        return Response.ok(new ApiResponse<>(true, "Task retrieved", taskResponse)).build();
    }

    @DELETE
    @Secured
    @Path("/{taskId}")
    @RolesAllowed({Enums.ROLES.USER, Enums.ROLES.ADMIN})
    public Response deleteTask(@PathParam("taskId") String taskId) {
        taskService.deleteTask(taskId);
        return Response.ok(new ApiResponse<>(true, "Task deleted")).build();
    }
}

//@HeaderParam("userId") String userId,
//@HeaderParam("role") String role