package com.example.springsterterdemo.integration;

import com.example.springsterterdemo.integration.annotation.CustomAnnotationForIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@CustomAnnotationForIntegrationTest
//@Sql({"classpath:sql/data.sql"})
public abstract class TestIntegrationBase {
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:16");
    @BeforeAll
    static void start(){
        container.start();
    }
    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        System.out.println("===========================================================");
        registry.add("spring.datasource.url", () -> container.getJdbcUrl());
    }

}
