package com.example.springsterterdemo.DTO.mapper;


import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.entity.Department;

public class DepartmentMapper {

        public static DepartmentReadDto createResponseDTO(Department department){

            return DepartmentReadDto.builder()
                    .id(department.getId())
                    .departmentName(department.getDepartmentName())
//                    .cityAndRegion(department.getCityAndRegion())
                    .build();
        }
    public static Department createDepartmentEntity(DepartmentCreateEditDto requestDto){

        Department department = Department.builder()
                .departmentName(requestDto.getDepartmentName())
                .listOfEmployees(requestDto.getListOfEmployees())
                .build();
        department.getCityAndRegion().put(requestDto.getCity(), requestDto.getRegion());
        return department;
    }
}
