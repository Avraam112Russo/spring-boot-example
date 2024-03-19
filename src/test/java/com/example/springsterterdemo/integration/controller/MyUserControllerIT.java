package com.example.springsterterdemo.integration.controller;

import com.example.springsterterdemo.http.DTO.MyUserReadDTO;
import com.example.springsterterdemo.integration.TestIntegrationBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.core.IsEqual;
import org.testcontainers.shaded.org.hamcrest.core.IsInstanceOf;

import java.time.LocalDate;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class MyUserControllerIT extends TestIntegrationBase {
    private final MockMvc mockMvc;

    @Test
    void saveNewUser_Success() throws Exception {
        MyUserReadDTO myUserReadDTO = MyUserReadDTO.builder()
                .birthDate(LocalDate.parse("2000-08-16"))
                .username("some_username")
                .email("janrus@yandex.ru")
                .build();
         mockMvc.perform(MockMvcRequestBuilders.post("/api/v6/user")
                .queryParam("username", myUserReadDTO.getUsername())
                .queryParam("email", myUserReadDTO.getEmail())
                .queryParam("birthDate", myUserReadDTO.getBirthDate().toString())

        )
                 .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
