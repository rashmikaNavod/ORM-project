package org.example.custom_exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super(message);
    }
}
