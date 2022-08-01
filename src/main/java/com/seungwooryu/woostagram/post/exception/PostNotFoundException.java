package com.seungwooryu.woostagram.post.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends CustomException {
    public PostNotFoundException() {
        super(HttpStatus.BAD_REQUEST);
    }
}

