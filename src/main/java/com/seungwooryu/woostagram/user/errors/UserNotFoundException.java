package com.seungwooryu.woostagram.user.errors;

import org.springframework.validation.FieldError;

import java.util.List;

public class AuthenticationException extends CustomException {
    public AuthenticationException(List<FieldError> fieldErrors) {
        super(fieldErrors);
    }
}
