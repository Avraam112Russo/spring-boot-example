package com.example.springsterterdemo.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/api/v1")
@SessionAttributes("user2")
@Slf4j
public class GreetingController {

//    @GetMapping("/hello/{NAME}")
//    public ModelAndView helloGreeting(ModelAndView modelAndView,
//                                      @RequestParam(value = "name", required = false) String name,
//                                      @RequestHeader("accept") String accept,
//                                      @CookieValue("JSESSIONID") String jSessionId,
//                                      // fetch cookie
//                                      @CookieValue(value = "NAME", required = false)String nameFromCookie,
//                                      @PathVariable("NAME")String empName,
//                                      HttpServletResponse response
//                                      ){
//        modelAndView.setViewName("greeting/hello");
//        if (nameFromCookie == null){
//            // set Cookie
//        response.addCookie(new Cookie("NAME", "RUSSO"));
//        }
//        log.info(empName);
//        log.info(name);
//        log.info(accept);
//        log.info(jSessionId);
//        return modelAndView;
//    }
    @GetMapping("/bye")
    public ModelAndView byeGreeting(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", "Russo");
        // set session Attribute

        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
//    @GetMapping("/test")
//    public ModelAndView testMethod(
//            // fetch session attribute
//            @SessionAttribute("user2") UserDto dto){
//        ModelAndView modelAndView = new ModelAndView();
//        log.info(dto.toString());
//        modelAndView.setViewName("greeting/bye");
//        return modelAndView;
//    }
}
