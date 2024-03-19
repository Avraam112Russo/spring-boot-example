package com.example.springsterterdemo.http.controller;

import com.example.springsterterdemo.http.DTO.LoginDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

//@Controller
//@RequestMapping("/api/v3")
//public class LoginController {
//    @GetMapping("/login")
//    public String getLoginPPage(){
//        return "/login/login";
//    }
//    @PostMapping("/login")
//    public String getSuccessMessage(@ModelAttribute("login")LoginDto loginDto){
//        System.out.println(loginDto);
//        return "/login/success";
//    }
//    @GetMapping("/testRedirect")
//    public String getRedirect(){
//        // redirect
//        return "redirect:/api/v1/dep/getAll";
//    }
//
//}
