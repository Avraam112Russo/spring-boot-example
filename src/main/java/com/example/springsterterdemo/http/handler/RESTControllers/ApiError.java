package com.example.springsterterdemo.http.handler.RESTControllers;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Data
@Builder
public class ApiError{
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
