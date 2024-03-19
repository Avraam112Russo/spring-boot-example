package com.example.springsterterdemo.integration.controller;

import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.DTO.request.EmployeeCreateEditDto;
import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.integration.TestIntegrationBase;
import com.example.springsterterdemo.integration.annotation.CustomAnnotationForIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
//@CustomAnnotationForIntegrationTest
//@ExtendWith(SpringExtension.class)
public class DepartmentControllerIT extends TestIntegrationBase{
    // use test data base from TestIntegrationBase
    // each test liquibase created database and values
    @Autowired
    private  MockMvc mockMvc;

    @Test
    void getAllDepartmentsTest() throws Exception {
        mockMvc.perform(get("/api/v5/department")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("/department/Deps"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deps"))
//                .andExpect(MockMvcResultMatchers.model().attribute("deps", IsCollectionWithSize.hasSize(4)))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void saveDepartmentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v5/department")
                .param(DepartmentCreateEditDto.Fields.departmentName, "SALES")
                .param(DepartmentCreateEditDto.Fields.city, "SOCHI")
                .param(DepartmentCreateEditDto.Fields.region, "50")

                ).andExpect(MockMvcResultMatchers.view().name("department/Deps"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("deps"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void checkDateConverter() throws Exception {
        mockMvc.perform(get("/api/v5/department/checkDateFormat")
                .queryParam("date", "2000-08-16")
                        // convert String (requestParam) to LocalDate
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void checkDateTimeConverter() throws Exception {
        mockMvc.perform(get("/api/v5/department/checkDateAndTimeFormat")
                .queryParam("dateTime", "2019-03-27T10:15:30")
                // convert String (requestParam) to LocalDateTime
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void getDepartmentByIdTest() throws Exception {
        Integer departmentId = 6;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v5/department/{id}", departmentId))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("department/ById"))
                .andExpect(MockMvcResultMatchers.model().attribute("department", IsInstanceOf.instanceOf(DepartmentReadDto.class)))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void updateDepartmentById() throws Exception {
        Integer departmentId = 6;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v5/department/update/{id}", departmentId)
                .param("name", "someNewDepName")
        )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("department/ById"))
                .andExpect(MockMvcResultMatchers.model().attribute("department", IsInstanceOf.instanceOf(DepartmentReadDto.class)))
                .andDo(MockMvcResultHandlers.print());
    }


}
