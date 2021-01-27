package com.lms.gj_jewelry.exception;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(Long id) {
        super("Could not find user id, " + id);
    }
}
