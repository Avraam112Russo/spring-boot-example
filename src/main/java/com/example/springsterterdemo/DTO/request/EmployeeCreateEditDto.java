package com.example.springsterterdemo.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeCreateEditDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String departmentName;
}
