package com.cyl.springboottest1;

import com.cyl.springboottest1.fli.Myfilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
//@EnableCaching
@MapperScan(basePackages = {"com.cyl.springboottest1.mappers"})
public class Springboottest1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboottest1Application.class, args);
    }
   /* @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new Myfilter());
        registration.addUrlPatterns("/*"); //
        registration.addInitParameter("paramName", "paramValue"); //
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;

    }*/

}
