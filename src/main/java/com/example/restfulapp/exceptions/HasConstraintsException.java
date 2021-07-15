package com.example.restfulapp.exceptions;

public class HasConstraintsException extends RuntimeException {
    public HasConstraintsException(Long id) {
        super(String.format("Unable to delete entity with id %s because it has constraints", id));
    }

    public HasConstraintsException(String message) {
        super(message);
    }
}
