package com.taskTracking.security;

import com.taskTracking.common.Enums;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAllowed {
    Enums.ROLES[] value();
}