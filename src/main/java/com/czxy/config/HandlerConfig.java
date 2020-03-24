package com.czxy.config;

import com.czxy.user.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class HandlerConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginFilter loginFilter;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginFilter);
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns("/user/login");
    }
}
