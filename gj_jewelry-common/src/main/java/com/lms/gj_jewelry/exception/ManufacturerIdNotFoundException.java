package com.lms.gj_jewelry.exception;

public class ManufacturerIdNotFoundException extends RuntimeException {
    public ManufacturerIdNotFoundException(Long id) {
        super("Could not find manufacturer id, " + id);
    }
}
