package com.seungwooryu.woostagram.common.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.seungwooryu.woostagram.common.utils.ApiUtils.ApiResult;
import static com.seungwooryu.woostagram.common.utils.ApiUtils.error;

@RestControllerAdvice
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
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(error(e.getMessage(), httpStatus), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResult<?>> handleDuplicatedArgumentException(CustomException e) {
        final HttpStatus httpStatus = e.getHttpStatus();
        return new ResponseEntity<>(error(e.getMessage(), httpStatus), httpStatus);
    }
}
