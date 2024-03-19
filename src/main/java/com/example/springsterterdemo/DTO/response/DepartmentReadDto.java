package com.example.springsterterdemo.DTO.response;

import com.example.springsterterdemo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentReadDto {
    private Integer id;
    private String departmentName;
    private Map<String, Integer> cityAndRegion;
    private List<Employee> listOfEmployees;
}
