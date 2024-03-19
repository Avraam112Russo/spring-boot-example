package com.example.springsterterdemo.integration;

import com.example.springsterterdemo.repository.DepartmentRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration //  // use this(TestApplicationRunner) ApplicationContext for all integration tests
public class TestApplicationRunner {
//    @MockBean
//    private  DepartmentRepository departmentRepository; // mockBean or mockSPy for all integration tests
}
