package com.lms.gj_jewelry.exception;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException(String email) {
        super("Could not find user email, " + email);
    }
}
