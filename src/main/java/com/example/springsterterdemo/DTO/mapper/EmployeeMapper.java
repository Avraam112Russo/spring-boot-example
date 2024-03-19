package com.example.springsterterdemo.DTO.mapper;

import com.example.springsterterdemo.DTO.request.EmployeeCreateEditDto;
import com.example.springsterterdemo.DTO.response.EmployeeReadDto;
import com.example.springsterterdemo.entity.Department;
import com.example.springsterterdemo.entity.Employee;
import com.example.springsterterdemo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("singleton")
@RequiredArgsConstructor
public class EmployeeMapper {

    private final DepartmentRepository departmentRepository;
    public EmployeeReadDto employeeToEmployeeResponseDto(Employee employee){
        return EmployeeReadDto.builder()
                .id(employee.getId())
                .userName(employee.getUserName())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .departmentName(employee.getDepartment().getDepartmentName())
                .build();
    }
    public Employee employeeRequestDtoToJustEmployee(EmployeeCreateEditDto employeeCreateEditDto){
        return Employee.builder()
                .userName(employeeCreateEditDto.getUserName())
                .firstName(employeeCreateEditDto.getFirstName())
                .lastName(employeeCreateEditDto.getLastName())
                .department(findDepartmentByName(employeeCreateEditDto.getDepartmentName()))
                .build();
    }
    private Department findDepartmentByName(String name){
        Optional<Department> mayBeDepartment = departmentRepository.findDepartmentByDepartmentName(name);
        return mayBeDepartment.orElseGet(()-> null);
    }
}
