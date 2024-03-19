package com.example.springsterterdemo.http.REST.RestController;

import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.service.departmentService.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dep")
@RequiredArgsConstructor
public class RestControllerDepartment {
    private final DepartmentService departmentService;


    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Integer id){
       return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
    }
    @PostMapping("/departments")
    public ResponseEntity<?> saveEmployee(@RequestBody DepartmentCreateEditDto requestDto){
        return ResponseEntity.ok(departmentService.saveDepartment(requestDto));

    }
    @GetMapping("/departmentsOrderBy")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllDepartmentsOrderBy(){
        return new ResponseEntity<>(departmentService.getAllDepartmentsOrderByName(), HttpStatus.OK);
    }

    @GetMapping("/departmentsByNameIn")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllByNamesIn(@RequestParam("listNames")List<String>listOfNAmes){
        return new ResponseEntity<>(departmentService.getAllDepartmentsByName_In(listOfNAmes), HttpStatus.OK);
    }
    @GetMapping("/departmentsGreaterThen")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllGreaterThen(){
        return new ResponseEntity<>(departmentService.getAllDepartmentsWhereListEmployeeGreaterThen(), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithListOfEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllDepartmentsWithListOfEmployee(){
        return new ResponseEntity<>(departmentService.getAllDepartmentsWithListOfEmployee(), HttpStatus.OK);
    }

    @GetMapping("/departmentsWhichContaining")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getListDepartmentsWhichContaining(@RequestParam("depName")String depName){
        return new ResponseEntity<>(departmentService.getListDepartmentsWhichContaining(depName), HttpStatus.OK);
    }
    @PutMapping("/departments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateDepartment(@RequestParam("depName")String depName, @RequestParam("id")Integer ids){
        return new ResponseEntity<>(departmentService.updateDepartment(depName, ids), HttpStatus.OK);
    }
    @GetMapping("/testNamedQuery")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> testNamedQuery(@RequestParam("depName")String depName){
        return new ResponseEntity<>(departmentService.testNamedQuery(depName), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithSorting")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllWithSorting(@RequestParam("sortProps")String sortProps){
        return new ResponseEntity<>(departmentService.findAllWithSorting(sortProps), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithPageable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllWithPageable(@RequestParam("pageNumber")Integer pageNumber,
                                                 @RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam("sortProps")String sortProps){
        return new ResponseEntity<>(departmentService.findAllWithPageable(pageNumber, pageSize, sortProps), HttpStatus.OK);
    }
//    @GetMapping("/findAllByAndWillReturnSlice")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> findAllByAndWillReturnSlice(){
//        departmentService.findAllByAndWillReturnSlice();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @GetMapping("/departmentsAndWillReturnPage")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllByAndWillReturnPage(@RequestParam("pageNumber")Integer pageNumber,
                                                        @RequestParam("pageSize") Integer pageSize,
                                                        @RequestParam("sortProps")String sortProps){
        return new ResponseEntity<>(departmentService.findAllByAndWillReturnPage(pageNumber, pageSize, sortProps), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithEntityGraph")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllWithEntityGraph(){
        return new ResponseEntity<>(departmentService.findAllWithEntityGraphAndCityRegion(), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithEntityGraphDynamic")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllWithEntityGraph_DynamicDefine(){
        return new ResponseEntity<>(departmentService.findAllWithEntityGraphAndCityRegion_DynamicDefine(), HttpStatus.OK);
    }
    @GetMapping("/departmentsWithQueryHint")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllWithQueryHint(){
        return new ResponseEntity<>(departmentService.findAllWithQueryHint(), HttpStatus.OK);
    }
    @GetMapping("/departmentsAndMapToProjection")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllDepsAndMapToProjectionById(@RequestParam("id")Integer id){
        return new ResponseEntity<>(departmentService.findDepartmentAndMapToProjection(id), HttpStatus.OK);
    }
    @GetMapping("/departmentsJoinEmployees")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEmployeeAndDepartmentProjectionByName(@RequestParam("name")String name){
        return new ResponseEntity<>(departmentService.getEmployeeAndDepartmentProjection(name), HttpStatus.OK);
    }
    @GetMapping("/allDepartmentsAndEmployees")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllEmployeeAndDepartmentProjection(){
        return new ResponseEntity<>(departmentService.getDepsAndEmployeesProjection(), HttpStatus.OK);
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("RestControllerEmployee postConstruct...");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("RestControllerEmployee preDestroy...");
    }
}
