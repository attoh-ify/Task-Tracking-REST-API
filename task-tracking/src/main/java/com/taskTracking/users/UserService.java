package com.taskTracking.users;

import com.taskTracking.common.Enums;
import com.taskTracking.common.dto.CreateUserRequest;
import com.taskTracking.common.dto.LoginRequest;
import com.taskTracking.common.dto.LoginResponse;
import com.taskTracking.common.dto.UserResponse;
import com.taskTracking.common.exceptions.BadRequestException;
import com.taskTracking.common.utils.JWTUtils;
import com.taskTracking.common.utils.PasswordUtils;
import com.taskTracking.logger.Logged;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Logged
@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public UserResponse createUser(CreateUserRequest request) {
        String username = request.getUsername();
        if (userDAO.findByUsername(username) != null) {
            throw new BadRequestException("User with the username provided already exists");
        }

        User user = new User(
                username,
                PasswordUtils.hashPassword(request.getPassword()),
                Enums.ROLES.USER
        );

        userDAO.save(user);
        return new UserResponse(user);
    }

    public UserResponse createAdmin(CreateUserRequest request) {
        String username = request.getUsername();
        if (userDAO.findByUsername(username) != null) {
            throw new BadRequestException("Admin with the username provided already exists");
        }

        User user = new User(
                username,
                PasswordUtils.hashPassword(request.getPassword()),
                Enums.ROLES.ADMIN
        );

        userDAO.save(user);
        return new UserResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new BadRequestException("Invalid username or password");
        }

        if (!PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
            throw new BadRequestException("Invalid username or password");
        }

        return new LoginResponse(JWTUtils.generateToken(user.getId(), user.getRole()));
    }
}
