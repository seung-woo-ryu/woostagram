package com.seungwooryu.woostagram.user.interceptor;

import com.seungwooryu.woostagram.user.errors.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SigninCheckInterceptor implements HandlerInterceptor {
    @Override
    // 로그인은 preHandle 만 있어도 됨
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("URI: {}", request.getRequestURI());
        log.debug("METHOD: {}", request.getMethod());

        /*HttpSession session = Optional.ofNullable(request.getSession(false))
                .orElseThrow(() -> {
                    log.info("미인증 사용자 요청");
                    throw new AuthenticationException();
                });*/
        HttpSession session = request.getSession(false);

        if (session == null) {
            log.info("미인증 사용자 요청");
            throw new AuthenticationException();
        }

        return true;
    }
}
