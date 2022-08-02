package com.seungwooryu.woostagram.user.exception;


import com.seungwooryu.woostagram.common.errors.CustomException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends CustomException {
    public AuthenticationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public AuthenticationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
