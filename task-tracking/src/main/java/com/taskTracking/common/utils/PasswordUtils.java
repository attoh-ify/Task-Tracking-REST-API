package com.taskTracking.common.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    private static final int ITERATIONS = 12000;
    private static final int KEY_LENGTH = 256;

    private PasswordUtils() {}

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}