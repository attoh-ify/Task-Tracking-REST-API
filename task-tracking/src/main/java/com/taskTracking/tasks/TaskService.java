package com.taskTracking.tasks;

import com.taskTracking.common.dto.CreateTaskRequest;
import com.taskTracking.common.dto.TaskResponse;
import com.taskTracking.common.exceptions.BadRequestException;
import com.taskTracking.events.TaskCreatedEvent;
import com.taskTracking.users.User;
import com.taskTracking.users.UserDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Event;

@Stateless
public class TaskService {
    @Inject
    private TaskDAO taskDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private Event<TaskCreatedEvent> taskCreatedEventEvent;

    public TaskResponse createTask(CreateTaskRequest request, String userId) {
        User user = userDAO.findById(userId);

        if (user == null) {
            throw new BadRequestException("User with the userId provided doesn\'t exist");
        }

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                user
        );

        taskDAO.save(task);

        taskCreatedEventEvent.fire(new TaskCreatedEvent(task));

        return new TaskResponse(task);
    }

    public TaskResponse getTaskById(String taskId) {
        Task task = taskDAO.findById(taskId);
        return new TaskResponse(task);
    }

    public List<TaskResponse> getTaskByUser(String userId) {
        List<Task> tasks = taskDAO.findByOwnerId(userId);
        List<TaskResponse> taskResponses = new ArrayList<TaskResponse>();

        for (Task task : tasks) {
            taskResponses.add(new TaskResponse(task));
        }
        return taskResponses;
    }

    public void deleteTask(String taskId) {
        Task task = taskDAO.findById(taskId);

        if (task == null) {
            throw new BadRequestException("Task with taskId provided doesn\'t exist");
        }

        taskDAO.delete(task);
    }
}
