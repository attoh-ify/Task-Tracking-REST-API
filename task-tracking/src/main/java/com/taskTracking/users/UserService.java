package com.taskTracking.users;

import com.taskTracking.common.Enums;
import com.taskTracking.common.dto.CreateUserRequest;
import com.taskTracking.common.dto.UserResponse;
import com.taskTracking.common.exceptions.BadRequestException;
import com.taskTracking.common.utils.PasswordUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public UserResponse createUser(CreateUserRequest request) {
        String username = request.getUsername();
        if (userDAO.findByUsername(username) != null) {
            throw new BadRequestException("PasswordUtils.hashPassword(request.getPassword())");
        }

        User user = new User(
                username,
                PasswordUtils.hashPassword(request.getPassword()),
                Enums.ROLES.USER
        );

        userDAO.save(user);
        return new UserResponse(user);
    }
}
