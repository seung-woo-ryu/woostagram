package com.seungwooryu.woostagram.common.utils;


import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ApiUtils {
    public static <T> ApiResult<T> success(List<T> response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status, List<ApiError.FieldError> FieldErrors) {
        return new ApiResult<>(false, null, new ApiError(status, FieldErrors));
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, null, new ApiError(status));
    }

    @ToString
    @Getter
    @RequiredArgsConstructor
    public static class ApiError {
        private final String message;
        private final List<FieldError> errors;
        private final int status;

        ApiError(HttpStatus status) {
            this(status, new ArrayList<FieldError>());
        }

        ApiError(HttpStatus status, List<FieldError> fieldErrors) {
            this.message = status.getReasonPhrase();
            this.status = status.value();
            this.errors = fieldErrors;
        }

        @Getter
        public static class FieldError {
            private String field;
            private Object value;
            private String reason;

            private FieldError(org.springframework.validation.FieldError fieldError) {
                this.field = fieldError.getField();
                this.value = (Object) fieldError.getRejectedValue();
                this.reason = fieldError.getDefaultMessage();
            }
            public static FieldError createFieldError(org.springframework.validation.FieldError fieldError) {
                return new FieldError(fieldError);
            }
        }

    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class ApiResult<T> {
        private final boolean success;
        private final List<T> response;
        private final ApiError error;

    }

}