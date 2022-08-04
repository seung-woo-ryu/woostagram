package com.seungwooryu.woostagram.common.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiUtils {
    public static <T> ApiResult<T> success(List<T> multipleReponse) {
        return new ApiResult<T>(true, multipleReponse, null);
    }

    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<T>(true, Arrays.asList(response), null);
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(true, Collections.emptyList(), null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(false, Collections.emptyList(), new ApiError(message, status));
    }

    @ToString
    @Getter
    @RequiredArgsConstructor
    public static class ApiError {
        private final String message;
        private final int status;

        ApiError(String message, HttpStatus status) {
            this.message = message;
            this.status = status.value();
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
