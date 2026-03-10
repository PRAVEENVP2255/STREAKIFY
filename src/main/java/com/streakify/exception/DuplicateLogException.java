package com.streakify.exception;

public class DuplicateLogException extends RuntimeException {

    public DuplicateLogException(String message) {
        super(message);
    }
}