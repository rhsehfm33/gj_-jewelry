package com.lms.gj_jewelry.exception;

public class UserAccountNotFoundException extends RuntimeException {
    public UserAccountNotFoundException(String account) {
        super("Could not find user account, " + account);
    }
}
