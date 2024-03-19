package com.example.springsterterdemo.integration.annotation;

import com.example.springsterterdemo.SpringRunnerApplication;
import com.example.springsterterdemo.integration.TestApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Transactional
@SpringBootTest(classes = TestApplicationRunner.class) // use only one ApplicationContext for all integration tests
@ActiveProfiles(value = "sometest")// application-sometest.properties // -sometest

public @interface CustomAnnotationForIntegrationTest {
}
