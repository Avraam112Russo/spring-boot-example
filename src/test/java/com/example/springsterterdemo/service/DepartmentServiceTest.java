package com.example.springsterterdemo.service;

import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.entity.Department;
import com.example.springsterterdemo.repository.DepartmentRepository;
import com.example.springsterterdemo.service.departmentService.DepartmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentService departmentService;
    @Test
    void getAllDepartments_Success(){
        Mockito.doReturn(List.of(Department.builder().build(),
                Department.builder().build())).when(departmentRepository).findAll();
        List<DepartmentReadDto> allDepartments = departmentService.getAllDepartments();
        Assertions.assertThat(allDepartments).isNotNull();
        Assertions.assertThat(allDepartments).hasSize(2);
    }
}
