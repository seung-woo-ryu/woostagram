package com.seungwooryu.woostagram.user.errors;

import org.springframework.validation.FieldError;

import java.util.List;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(List<FieldError> fieldErrors) {
        super(fieldErrors);
    }
}