package com.cyl.springboottest1.utils;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器，未使用状态
 */
//@Configuration
public class FliConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInteceptor myInteceptor =new MyInteceptor();

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInteceptor);
        interceptorRegistration.addPathPatterns("/login/**");

    }

}
