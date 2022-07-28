package com.seungwooryu.woostagram.user;

import com.seungwooryu.woostagram.user.interceptor.SigninCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class UserConfig implements WebMvcConfigurer {
    private static final String[] whitelist = {"/signin", "/signup"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SigninCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(whitelist);
    }
}

