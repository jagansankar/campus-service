package com.campus.service.exception;

public class InvalidStaffException extends RuntimeException {

    public InvalidStaffException(String message) {
        super(message);
    }
}