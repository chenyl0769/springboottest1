package com.cyl.springboottest1.fli;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
public class FliConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInteceptor myInteceptor =new MyInteceptor();

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInteceptor);
        interceptorRegistration.addPathPatterns("/login/**");

    }

}
