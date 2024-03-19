package com.example.springsterterdemo.http.controller;

import com.example.springsterterdemo.http.DTO.OrderInfo;
import com.example.springsterterdemo.http.DTO.UserInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/api/v2")
@SessionAttributes(value = {"userSessionScope"})
public class UserController {


    @GetMapping(value = "/getInfo2")
    public String getUserAndOrderList(@ModelAttribute("userInfoDTO") UserInfoDTO userInfoDTO, // RequestScope
                               Model model,
                               @ModelAttribute("orderInfo") OrderInfo orderInfo){// RequestScope
                                // передаем поля наших объектов в параметре запроса и они автоматчески мапятся в сущности
        return "/user/getOrderInfo"; // и дальше эти сущности идут в jsp file
    }

    @GetMapping(value = "/setInfo")
    public ModelAndView setUserInfo(
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam Integer salary,
            ModelAndView modelAndView
    ){
        UserInfoDTO userInfoDto = UserInfoDTO.builder()
                .name(name)
                .lastName(lastName)
                .salary(salary)
                .build();
        modelAndView.addObject("userInfoDTO", userInfoDto); // add session attribute
        modelAndView.setViewName("/user/userInfo"); // set userInfo.jsp in response
        return modelAndView;
    }

    @GetMapping("/setUserData")
    public String setUserData(){
        return "/login/loginForREST";
    }

}
