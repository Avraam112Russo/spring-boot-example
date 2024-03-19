package com.example.springsterterdemo.integration.service;

import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.entity.Department;
import com.example.springsterterdemo.integration.TestIntegrationBase;
import com.example.springsterterdemo.integration.annotation.CustomAnnotationForIntegrationTest;
import com.example.springsterterdemo.service.departmentService.DepartmentService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

//@CustomAnnotationForIntegrationTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)// created new ApplicationContext after each test method
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // enable RequiredArgsConstructor for tests
@RequiredArgsConstructor
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // enable RequiredArgsConstructor for tests
@CustomAnnotationForIntegrationTest
public class DepartmentServiceIT extends TestIntegrationBase {
    private final DepartmentService departmentService;

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
//    private final DepartmentRepository departmentRepository; // mockBean from TestApplicationRunner.class
    @Test
    @Transactional
    void getAllDepartmentsTest(){
        List<DepartmentReadDto> allDepartments = departmentService.getAllDepartments();
        Assertions.assertNotNull(allDepartments);
//        org.assertj.core.api.Assertions.assertThat(allDepartments).hasSize(2);
        log.info(allDepartments.toString());
    }

    @Test
    void checkPostgresqlContainerTest(){
        DepartmentCreateEditDto dto = DepartmentCreateEditDto.builder()
                .departmentName("TEST")
                .city("MOSCOW")
                .region(77)
                .build();
        DepartmentReadDto response = departmentService
                .saveDepartment(dto);
        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
    }


    @Test
    @Transactional // TestContext managed our transactions in tests // we cam override BeforeTransaction and afterTransaction methods
    // @Rollback default annotations for tests // TestContext does rollback
    @Commit
    void saveDepartmentTest(){
         entityManager.persist(Department.builder().departmentName("SECURITY").build());

    }
    @Test
    void saveDepartmentWithManualManagedTransaction(){
        transactionTemplate.executeWithoutResult(transactionStatus ->
                entityManager.persist(Department.builder().departmentName("MILITARY").build()));
        List<Department> allDeps = transactionTemplate.execute(tr ->
                entityManager.createQuery("select dep from Department dep", Department.class).getResultList());

        org.assertj.core.api.Assertions.assertThat(allDeps).isNotNull();
//        org.assertj.core.api.Assertions.assertThat(allDeps).hasSize(6);
    }

}
