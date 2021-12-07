package com.lms.gj_jewelry.exception;

public class ItemIdNotFoundException extends RuntimeException {
    public ItemIdNotFoundException(Long id) {
        super("Could not find item id, " + id);
    }
}
