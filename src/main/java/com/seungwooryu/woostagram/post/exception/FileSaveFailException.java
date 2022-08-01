package com.seungwooryu.woostagram.post.exception;

import com.seungwooryu.woostagram.common.errors.CustomException;
import org.springframework.http.HttpStatus;

public class FileSaveFailException extends CustomException {
    public FileSaveFailException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
