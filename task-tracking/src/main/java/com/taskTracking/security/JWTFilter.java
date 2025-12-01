package com.taskTracking.security;

import com.taskTracking.common.Enums;
import com.taskTracking.common.dto.RequestContext;
import com.taskTracking.common.exceptions.UnauthorizedException;
import com.taskTracking.common.utils.JWTUtils;
import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class JWTFilter implements ContainerRequestFilter {
    @Inject
    private RequestContext requestContext;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String authHeader = ctx.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring("Bearer ".length());

        try {
            Claims claims = JWTUtils.validateToken(token).getBody();
            ctx.setProperty("userId", claims.getSubject());
            ctx.setProperty("role", claims.get("role"));

            requestContext.setUserId(claims.getSubject());
            requestContext.setRole(Enums.ROLES.valueOf(claims.get("role", String.class)));
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid or expired token");
        }
    }
}
