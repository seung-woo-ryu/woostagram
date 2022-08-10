package com.seungwooryu.woostagram.follow.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class FollowNotFoundException extends CustomException {
    public FollowNotFoundException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
