package com.lms.gj_jewelry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomerApiUserErrorAdvice extends UserErrorAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserAccountNotFoundException.class)
    public String handleUserAccountNotFoundException() {
        return "";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserEmailNotFoundException.class)
    public String handleUserEmailNotFoundException() {
        return "";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserPhoneNumberNotFoundException.class)
    public String handleUserPhoneNumberNotFoundException() {
        return "";
    }

}
