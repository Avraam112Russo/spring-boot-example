package com.example.springsterterdemo.DTO.projections;

import org.springframework.beans.factory.annotation.Value;

public interface DepartmentEmployeeProjection {
    Integer getDepartmentId();// method name and alias departmentId, departmentName should be same
    String getDepartmentName();
    String getFirstName();
//    String getLastName();
    @Value("#{target.lastname}") // target == projection object from DataBase, lastname is an alias
    String getSurName();
}
