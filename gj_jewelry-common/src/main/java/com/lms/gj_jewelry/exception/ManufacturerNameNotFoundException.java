package com.lms.gj_jewelry.exception;

public class ManufacturerNameNotFoundException extends RuntimeException {
    public ManufacturerNameNotFoundException(String name) {
        super("Could not find manufacturer name, " + name);
    }
}
