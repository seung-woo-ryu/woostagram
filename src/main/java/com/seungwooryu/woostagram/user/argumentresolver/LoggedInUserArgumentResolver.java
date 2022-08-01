package com.seungwooryu.woostagram.user.argumentresolver;

import com.seungwooryu.woostagram.user.annotation.LoggedInUser;
import com.seungwooryu.woostagram.user.dto.UserDto;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.seungwooryu.woostagram.common.utils.Constants.LOGGED_IN_USER_SESSION_KEY;

public class LoggedInUserArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoggedInUser.class)
                && parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        UserDto userDto = (UserDto) request.getSession().getAttribute(LOGGED_IN_USER_SESSION_KEY);

        return userDto.getEmail();
    }
}
