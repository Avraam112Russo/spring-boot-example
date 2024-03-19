package com.example.springsterterdemo.repository;

import com.example.springsterterdemo.DTO.projections.DepartmentEmployeeProjection;
import com.example.springsterterdemo.DTO.projections.DepartmentProjection;
import com.example.springsterterdemo.entity.Department;
import com.example.springsterterdemo.entity.Employee;
import com.example.springsterterdemo.entity.revision.Revision;
import jakarta.persistence.QueryHint;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer>,
        RevisionRepository<Department, Integer, Integer> { // type, primaryKey, primaryKey in Revision Entity
    Optional<Department> findDepartmentByDepartmentName(String name);
    List<Department> findAllByOrderByDepartmentName();
    List<Department> findAllByListOfEmployeesGreaterThanEqual(List<Employee> listOfEmployees);
    List<Department> findByDepartmentNameIn(List<String> names);

    //@namedQuery
    List<Department> testNamedQuery(@Param("depName")String depName);
    @Query(value = "select dep from Department dep join fetch dep.listOfEmployees join fetch dep.cityAndRegion")
    List<Department> getAllDepartmentsWithListOfEmployeeAndCityRegion();

    @Query(value = "select dep from Department dep where UPPER(dep.departmentName) like %:depName%")
    List<Department> getListDepartmentsWhichContaining(@Param("depName")String depName);
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    //clear persistContext and run session flush
    @Query(value = "update Department dep set dep.departmentName = :newDepartmentName where dep.id = :depId")
    @Transactional
    int updateDepartment(@Param("newDepartmentName") String newDepartmentName,@Param("depId") Integer depId);
    List<Department> findAllBy(Sort sort);
//    List<Department> findAllBy(Pageable pageable);
//    Slice<Department> findAllBy(Pageable pageable);
    Page<Department> findAllBy(Pageable pageable); // we can define limit and offset // pagination
    @EntityGraph(value = "Department.listOfEmployees") // entityGraph define in Entity
    @Query(value = "select dep from Department dep")
    List<Department> findAllWithListEmployees();
    @EntityGraph(attributePaths = {"listOfEmployees", "cityAndRegion"}) // same EntityGraph how and above // dynamic defined entityGraph
    @Query(value = "select dep from Department dep")
    @Cacheable(value = "testCacheDep")
    List<Department> findAllWithListEmployeesAndCityRegion();
    //@Lock(LockModeType.OPTIMISTIC) // required version field in entity
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "select dep from Department dep")
    List<Department> findAllWithQueryHint();

    @Query(nativeQuery = true,
            value = "select department_id departmentId, department_name departmentName from t_departments where department_id=:depId")
    DepartmentProjection findDepartmentById(@Param("depId")Integer id);
    @Query(nativeQuery = true,
            value = "select t_departments.department_id  departmentId, department_name departmentName, first_name firstname, last_name lastname from t_departments\n" +
                    "inner join t_employees on t_departments.department_id = t_employees.department_id where first_name = :firstName"
    )
    DepartmentEmployeeProjection getEmployeeAndDepartmentProjection(@Param("firstName")String firstName);
     // alias departmentId, departmentName should be same how in ProjectionInterface
    @Query(nativeQuery = true,
            value = "select t_departments.department_id departmentId, department_name departmentName, first_name firstName, last_name lastName" +
                    " from t_departments inner join t_employees on t_departments.department_id=t_employees.department_id"
    )
    List<DepartmentEmployeeProjection> getDepsAndEmployeesProjection();
}
