package com.example.springsterterdemo.http.controller;

import com.example.springsterterdemo.http.DTO.MyUserCreateEditDTO;
import com.example.springsterterdemo.http.DTO.MyUserReadDTO;
import com.example.springsterterdemo.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v6/user")
@Slf4j
public class MyUserController {
    private final UserService userService;



    @ExceptionHandler(Exception.class)
    public String saveNewUserExceptionHandler(Exception exception){
        log.error("Some error occurred: ", exception);
        return "error/error500";

    }


    @PostMapping
    public ModelAndView saveUser(@ModelAttribute @Validated MyUserCreateEditDTO createEditDTO,
                                  // important! BindingResult object should be right after object fro validation(createEditDTO)
                                  // if we have come errors by validation form, so we need bindingResult object
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView){
        if (bindingResult.hasErrors()){
            modelAndView.addObject("errors", bindingResult.getAllErrors());
            modelAndView.setViewName("error/errorPage");
            return modelAndView;
        }
        MyUserReadDTO myUserReadDTO = userService.saveNewUser(createEditDTO);
        modelAndView.addObject("user", myUserReadDTO);
        modelAndView.setViewName("myUser/user");
        return modelAndView;
    }
    @GetMapping
    public String getPageWithSaveUser(){
        return "myUser/CreateUser";
    }

    @PostMapping("/saveNew")
    public ModelAndView saveUser(@ModelAttribute @Validated MyUserCreateEditDTO createEditDTO,
                                 ModelAndView modelAndView){
        MyUserReadDTO myUserReadDTO = userService.saveNewUser(createEditDTO);
        modelAndView.addObject("user", myUserReadDTO);
        modelAndView.setViewName("myUser/user");
        return modelAndView;
    }

    @GetMapping("/getSingle/{id}")
    public ModelAndView getUserById(@PathVariable Long id, ModelAndView modelAndView){
        MyUserReadDTO myUserReadDTO = userService.getUserById(id);
        modelAndView.addObject("user", myUserReadDTO);
        modelAndView.setViewName("myUser/user");
        return modelAndView;
    }


}
