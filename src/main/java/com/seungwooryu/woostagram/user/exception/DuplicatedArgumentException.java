package com.seungwooryu.woostagram.user.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class DuplicatedArgumentException extends CustomException {
    public DuplicatedArgumentException(List<FieldError> fieldErrors) {
        super(fieldErrors, HttpStatus.BAD_REQUEST);
    }
}
