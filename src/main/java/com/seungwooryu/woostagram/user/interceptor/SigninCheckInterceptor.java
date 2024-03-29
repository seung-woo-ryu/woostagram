package com.seungwooryu.woostagram.user.interceptor;

import com.seungwooryu.woostagram.user.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class SigninCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("URI: {}", request.getRequestURI());
        log.debug("METHOD: {}", request.getMethod());

        Optional.ofNullable(request.getSession(false)).orElseThrow(() -> {
            log.info("미인증 사용자 요청");
            throw new AuthenticationException();
        });

        return true;
    }
}
