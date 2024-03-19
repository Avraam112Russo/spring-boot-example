package com.example.springsterterdemo.http.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
public class UserInfoDTOSessionScope {
        private String name;
        private String lastName;
        private Integer salary;
}
