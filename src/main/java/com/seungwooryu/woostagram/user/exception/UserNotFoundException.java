package com.seungwooryu.woostagram.user.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
