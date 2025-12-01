package com.taskTracking.security;

import com.taskTracking.common.Enums;
import com.taskTracking.common.exceptions.UnauthorizedException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;

@Provider
@Priority(Priorities.AUTHORIZATION)
@RolesAllowed({})
public class RoleFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        RolesAllowed rolesAllowed = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
        if (rolesAllowed == null) {
            // check class-level annotation if method-level is absent
            rolesAllowed = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
            if (rolesAllowed == null) {
                return; // no restrictions
            }
        }

        String roleString = (String) ctx.getProperty("role");

        if (roleString == null) {
            throw new UnauthorizedException("Missing role");
        }

        Enums.ROLES userRole;
        try {
            userRole = Enums.ROLES.valueOf(roleString);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid role");
        }

        if (Arrays.stream(rolesAllowed.value()).noneMatch(r -> r.equals(userRole))) {
            throw new UnauthorizedException("You do not have permission to access this resource");
        }
    }
}
