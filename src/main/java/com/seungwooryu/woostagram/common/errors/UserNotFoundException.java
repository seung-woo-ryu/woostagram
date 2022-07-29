package com.seungwooryu.woostagram.common.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(List<FieldError> fieldErrors, HttpStatus httpStatus) {
        super(fieldErrors, httpStatus);
    }
}
