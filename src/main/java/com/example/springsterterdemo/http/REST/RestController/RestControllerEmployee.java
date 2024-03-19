package com.example.springsterterdemo.http.REST.RestController;

import com.example.springsterterdemo.DTO.request.EmployeeCreateEditDto;
import com.example.springsterterdemo.service.employeeService.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class RestControllerEmployee {
    private final EmployeeService employeeService;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody List<EmployeeCreateEditDto> requestDtoList){
        return new ResponseEntity<>(requestDtoList.stream().map(
                employee -> employeeService.saveEmployee(employee)
        ).collect(Collectors.toList()), HttpStatus.CREATED);
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("RestControllerEmployee postConstruct...");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("RestControllerEmployee preDestroy...");
    }
}
