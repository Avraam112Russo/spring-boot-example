package com.example.springsterterdemo.http.handler.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.example.springstarterdemo.http.controller")
// only for @Controller
public class GlobalExceptionHandler{/* extend ResponseEntityExceptionHandler */
//    @ExceptionHandler({BindException.class, Exception.class})
    @ExceptionHandler(Exception.class)
    public String saveNewUserExceptionHandler(Exception exception){
        log.error("Some error occurred: ", exception);
        return "error/error500";

    }

}
