package com.seungwooryu.woostagram.user.errors;

import com.seungwooryu.woostagram.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.*;
import static java.util.stream.Collectors.toList;

@RestControllerAdvice(basePackageClasses = UserController.class)
@Slf4j
public class GeneralExceptionHandler {

    /**
     * javax.validation.Valid or @Validated에 @RequestBody, @RequestPart으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *
     * @ModelAttribute로 바인딩 exception시에는 BindException error 발생.
     */

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ApiError.FieldError> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(ApiError.FieldError::createFieldError)
                .collect(toList());

        final ApiResult<?> error = error(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST, fieldErrors);

        log.error("ApiResult = {}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResult<?>> handleDuplicatedArgumentException(CustomException e) {
        List<ApiError.FieldError> fieldErrors = Optional.ofNullable(e.getFieldErrors())
                .orElseGet(Collections::emptyList)
                .stream().filter(Objects::nonNull)
                .map(ApiError.FieldError::createFieldError)
                .collect(toList());

        final HttpStatus httpStatus = e.getHttpStatus();
        final ApiResult<?> error = error(httpStatus.getReasonPhrase(), httpStatus, fieldErrors);

        log.error("ApiResult = {}", error);
        return new ResponseEntity<>(error, httpStatus);
    }
}
