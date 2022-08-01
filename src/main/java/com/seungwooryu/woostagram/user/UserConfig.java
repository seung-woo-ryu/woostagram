package com.seungwooryu.woostagram.user;

import com.seungwooryu.woostagram.user.argumentresolver.LoggedInUserArgumentResolver;
import com.seungwooryu.woostagram.user.interceptor.SigninCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class UserConfig implements WebMvcConfigurer {
    private static final String[] whitelist = {"/signin", "/signup", "/post"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SigninCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(whitelist);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoggedInUserArgumentResolver());
    }
}

