package com.seungwooryu.woostagram.user.errors;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends CustomException {
    public AuthenticationException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
