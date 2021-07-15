package com.example.restfulapp.exceptions;

public class NotDeletedException extends RuntimeException {

    public NotDeletedException(Class<?> aClass) {
        super(String.format("The entity %s was not deleted", aClass.getName()));
    }

    public NotDeletedException(String message) {
        super(message);
    }
}
