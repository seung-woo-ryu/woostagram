package com.seungwooryu.woostagram.user.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicatedArgumentException extends CustomException {
    public DuplicatedArgumentException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public DuplicatedArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
