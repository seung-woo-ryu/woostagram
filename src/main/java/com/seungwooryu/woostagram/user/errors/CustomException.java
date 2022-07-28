package com.seungwooryu.woostagram.user.errors;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class CustomException extends RuntimeException {
    private final List<FieldError> fieldErrors;

    public CustomException(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
