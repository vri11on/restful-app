package com.example.restfulapp.exceptions;

import com.example.restfulapp.entity.BaseEntity;

public class IdNotPresentedException extends RuntimeException {
    public IdNotPresentedException(Class<?> aClass) {
        super(String.format("Id of entity %s is not presented", aClass.getName()));
    }

    public IdNotPresentedException(String message) {
        super(message);
    }
}
