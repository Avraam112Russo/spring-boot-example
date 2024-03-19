package com.example.springsterterdemo.http.REST.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRestDto {
    private String name;
    private String lastName;
    private Integer age;
}
