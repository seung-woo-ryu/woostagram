package com.seungwooryu.woostagram.like.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class LikeNotExistsException extends CustomException {
    public LikeNotExistsException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
