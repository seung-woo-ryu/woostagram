package com.seungwooryu.woostagram.common.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(HttpStatus httpStatus) {
        this(httpStatus.getReasonPhrase(), httpStatus);
    }
}
