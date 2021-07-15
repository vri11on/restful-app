package com.example.restfulapp.exceptions;

public class UnsupportedLimitException extends RuntimeException {
    public UnsupportedLimitException(Integer limit) {
        super(String.format("The presented limit %d is not supported. Please select the limit less than 1000", limit));
    }

    public UnsupportedLimitException(String message) {
        super(message);
    }
}
