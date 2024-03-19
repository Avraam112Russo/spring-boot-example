package com.example.springsterterdemo.http.controller;

import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.service.departmentService.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/v5/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    @GetMapping
    public ModelAndView getAllDepartments(ModelAndView modelAndView){
        List<DepartmentReadDto> allDepartments = departmentService.getAllDepartments();
        modelAndView.addObject("deps", allDepartments);
        modelAndView.setViewName("/department/Deps");
        return modelAndView;
    }
    @PostMapping
    public ModelAndView saveDepartment(@ModelAttribute DepartmentCreateEditDto createEditDto, ModelAndView modelAndView){
        System.out.println();
         departmentService.saveDepartment(createEditDto);
        List<DepartmentReadDto> allDepartments = departmentService.getAllDepartments();
        modelAndView.addObject("deps", allDepartments);
        modelAndView.setViewName("department/Deps");
         return modelAndView;
    }
    @PostMapping("/update/{id}")
    public ModelAndView updateDepartmentById(@PathVariable Integer id, @RequestParam("name")String name, ModelAndView modelAndView){
        if (departmentService.updateDepartment(name, id) > 0) ;
        modelAndView.addObject("department", departmentService.getDepartmentById(id));
        modelAndView.setViewName("department/ById");
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView getDepartmentById(@PathVariable Integer id, ModelAndView modelAndView){
        DepartmentReadDto departmentById = departmentService.getDepartmentById(id);
        modelAndView.addObject("department", departmentById);
        modelAndView.setViewName("department/ById");
        return modelAndView;
    }
    @GetMapping("/setNewName/{id}")
    public ModelAndView getDepartmentByIdForUpdate(@PathVariable Integer id, ModelAndView modelAndView){
        DepartmentReadDto departmentById = departmentService.getDepartmentById(id);
        modelAndView.addObject("department", departmentById);
        modelAndView.setViewName("department/UpdateDepartmentById");
        return modelAndView;
    }
    @GetMapping("/newDep")
    public String getNewDepartmentPage(){
        return "/department/newDep";
    }
    @GetMapping("/checkDateFormat")
    //@Test
    //.queryParam("dateTime", "2019-03-27")
    //                // convert String (requestParam) to LocalDateT
    public void checkDateFormat(@RequestParam("date") LocalDate date){
        System.out.println(date.toString());
    }
    @GetMapping("/checkDateAndTimeFormat")
    //@Test
    //.queryParam("dateTime", "2019-03-27T10:15:30")
    //                // convert String (requestParam) to LocalDateTime
    // @DateTimeFormat(pattern = YYYY-MM-dd) over the field in Entity works same
    public void checkDateAndTimeFormat(@RequestParam("dateTime") LocalDateTime date){
        System.out.println(date.toString());
    }
}
