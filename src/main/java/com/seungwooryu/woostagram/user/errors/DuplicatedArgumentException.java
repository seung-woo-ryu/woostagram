package com.seungwooryu.woostagram.user.errors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class DuplicatedArgumentException extends CustomException {
    public DuplicatedArgumentException(List<FieldError> fieldErrors, HttpStatus httpStatus) {
        super(fieldErrors, httpStatus);
    }
}
