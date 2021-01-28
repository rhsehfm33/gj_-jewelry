package com.lms.gj_jewelry.exception;

public class UserPhoneNumberNotFoundException extends RuntimeException {
    public UserPhoneNumberNotFoundException(String phoneNumber) {
        super("Could not find user phone number, " + phoneNumber);
    }
}
