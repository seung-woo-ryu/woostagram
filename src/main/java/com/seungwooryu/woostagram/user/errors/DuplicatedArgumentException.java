package com.seungwooryu.woostagram.user.errors;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
public class DuplicatedArgumentException extends RuntimeException {
    private List<FieldError> fieldErrors;

    public DuplicatedArgumentException( List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
