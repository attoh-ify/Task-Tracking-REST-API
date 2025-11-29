package com.taskTracking.security;

import com.taskTracking.common.exceptions.UnauthorizedException;
import com.taskTracking.common.utils.JWTUtils;
import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class JWTFilter implements ContainerRequestFilter {
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
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid or expired token");
        }
    }
}
