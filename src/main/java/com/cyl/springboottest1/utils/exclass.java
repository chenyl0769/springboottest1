package com.cyl.springboottest1.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局数据校验
 */
@ControllerAdvice
public class exclass {
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResponseEntity myex(BindException ex, HttpServletRequest req) {


        List<FieldError> errors = ex.getFieldErrors();
        ResponseEntity responseEntity =new ResponseEntity(errors, HttpStatus.OK);

        return  responseEntity;
    }

}