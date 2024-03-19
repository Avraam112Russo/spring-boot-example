package com.example.springsterterdemo.DTO.request;

import com.example.springsterterdemo.entity.Employee;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants // generate constants with field name
public class DepartmentCreateEditDto {

    private String departmentName;
    private String city;
    private Integer region;
    @Nullable
    @Builder.Default
    private List<Employee> listOfEmployees =new ArrayList<>();
}
