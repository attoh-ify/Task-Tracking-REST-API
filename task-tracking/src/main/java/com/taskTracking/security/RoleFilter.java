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

        Enums.ROLES userROle = (Enums.ROLES) ctx.getProperty("role");

        if (userROle == null || Arrays.stream(rolesAllowed.value()).noneMatch(r -> r.equals(userROle))) {
            throw new UnauthorizedException("You do not have permission to access this resource");
        }
    }
}
