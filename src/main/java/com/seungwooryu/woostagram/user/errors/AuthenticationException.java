package com.seungwooryu.woostagram.user.errors;


import java.util.Collections;

public class AuthenticationException extends CustomException {
    public AuthenticationException() {
        super(Collections.emptyList());
    }
}
