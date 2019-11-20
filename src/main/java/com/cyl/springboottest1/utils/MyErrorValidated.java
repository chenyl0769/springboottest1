package com.cyl.springboottest1.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyErrorValidated {

    public static void returnerror(BindingResult result, HttpServletRequest request) {
        List<ObjectError> allErrors = result.getAllErrors();
        request.setAttribute("errors", allErrors);
        System.out.println(allErrors);
    }
}
