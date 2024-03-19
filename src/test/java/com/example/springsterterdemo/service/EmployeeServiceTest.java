package com.example.springsterterdemo.service;

import com.example.springsterterdemo.entity.Employee;
import com.example.springsterterdemo.repository.EmployeeRepository;
import com.example.springsterterdemo.service.employeeService.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    Employee employee;
    @BeforeEach
    void prepare(){
        employee = Employee.builder()
                .userName("avraam112")
                .firstName("Avraam")
                .lastName("Russo")
                .build();
    }
    @InjectMocks
    private EmployeeService employeeService;
    @Test
    void getEmployeeById_SuccessTest(){

//        Mockito.verifyNoMoreInteractions(applicationEventPublisher, employeeService);
    }
    @Test
    void getAllEmployees_SuccessTest(){

    }
}
