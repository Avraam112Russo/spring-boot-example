package com.example.springsterterdemo.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeReadDto {

    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String departmentName;
}
