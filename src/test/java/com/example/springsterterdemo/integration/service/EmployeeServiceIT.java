package com.example.springsterterdemo.integration.service;

import com.example.springsterterdemo.integration.annotation.CustomAnnotationForIntegrationTest;
import com.example.springsterterdemo.service.employeeService.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestConstructor;

import static org.hamcrest.CoreMatchers.is;


@CustomAnnotationForIntegrationTest
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // created new ApplicationContext after each test method
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // enable @RequiredArgsConstructor for tests
// replace @TestConstructor we can add properties in spring.properties
public class EmployeeServiceIT {
    @Value("${spring.employee.firstName}")
    private String name;
    private final EmployeeService employeeService;
    @Test
    void findById() {

    }
    @Test
    void demo() {
        Assertions.assertThat(true).isTrue();
    }
}
