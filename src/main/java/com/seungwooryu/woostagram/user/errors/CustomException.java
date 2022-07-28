package com.seungwooryu.woostagram.user.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

@Getter
public class CustomException extends RuntimeException {
    private final List<FieldError> fieldErrors;
    private final HttpStatus httpStatus;

    public CustomException(List<FieldError> fieldErrors, HttpStatus httpStatus) {
        this.fieldErrors = fieldErrors;
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus) {
        this(Collections.EMPTY_LIST, httpStatus);
    }
}
