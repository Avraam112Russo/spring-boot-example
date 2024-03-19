package com.example.springsterterdemo.http.controller;

import com.example.springsterterdemo.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class MySpringSecurityLogin {
    private final UserService userService;

    @GetMapping
    public String getLoginPage(){
        return "springSecurity/login";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "springSecurity/success";
    }

}
