package com.example.springsterterdemo.DTO.projections;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface DepartmentProjection {
    Integer getDepartmentId();
    String getDepartmentName();// method name and alias departmentId, departmentName should be same
}
