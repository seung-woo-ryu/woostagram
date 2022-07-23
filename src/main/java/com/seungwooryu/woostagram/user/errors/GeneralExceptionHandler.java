package com.seungwooryu.woostagram.user.errors;

import com.seungwooryu.woostagram.common.utils.ApiUtils;
import com.seungwooryu.woostagram.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.*;
import static com.seungwooryu.woostagram.common.utils.ApiUtils.ApiError.*;
import static java.util.stream.Collectors.toList;

@RestControllerAdvice(basePackageClasses = UserController.class)
@Slf4j
public class GeneralExceptionHandler {

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ApiError.FieldError> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(ApiError.FieldError::createFieldError)
                .collect(toList());

        final ApiResult<?> error = error(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST, fieldErrors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
