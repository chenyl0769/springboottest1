package com.cyl.springboottest1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//关闭了redis缓存的使用
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
