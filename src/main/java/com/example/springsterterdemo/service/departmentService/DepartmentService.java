package com.example.springsterterdemo.service.departmentService;

import com.example.springsterterdemo.DTO.projections.DepartmentEmployeeProjection;
import com.example.springsterterdemo.DTO.projections.DepartmentProjection;
import com.example.springsterterdemo.DTO.request.DepartmentCreateEditDto;
import com.example.springsterterdemo.DTO.response.DepartmentReadDto;
import com.example.springsterterdemo.DTO.mapper.DepartmentMapper;
import com.example.springsterterdemo.entity.Department;
import com.example.springsterterdemo.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // optimization to hibernate
public class DepartmentService{

   private final DepartmentRepository departmentRepository;

   @Transactional
   public DepartmentReadDto saveDepartment(DepartmentCreateEditDto requestDto){
       Department department = DepartmentMapper.createDepartmentEntity(requestDto);
       department.getListOfEmployees().stream().forEach(emp -> emp.setDepartment(department));
        return DepartmentMapper.createResponseDTO(departmentRepository.save(department));
   }
   public List<DepartmentReadDto> getAllDepartments(){
       return departmentRepository.findAll().stream()
               .map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }
   public List<Department> getAllDepartmentsOrderByName(){
       return departmentRepository.findAllByOrderByDepartmentName();
   }

   public List<DepartmentReadDto> getAllDepartmentsWhereListEmployeeGreaterThen(){
       return departmentRepository.findAll().stream()
               .filter(dep -> dep.getListOfEmployees().size() > 0)
               .map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }
   public List<DepartmentReadDto> getAllDepartmentsByName_In(List<String> departmentNames){
       return departmentRepository.findByDepartmentNameIn(departmentNames).stream()
               .map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());

   }
   public List<DepartmentReadDto> getAllDepartmentsWithListOfEmployee(){// join fetch
       return departmentRepository.getAllDepartmentsWithListOfEmployeeAndCityRegion().stream()
               .map(dep -> {
                   DepartmentReadDto responseDTO = DepartmentMapper.createResponseDTO(dep);
                   responseDTO.setListOfEmployees(dep.getListOfEmployees());
                    return responseDTO;
               })
               .collect(Collectors.toList());
   }
   public List<DepartmentReadDto> testNamedQuery(String depName){
       return departmentRepository.testNamedQuery(depName).stream()
               .map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }

   public List<DepartmentReadDto> getListDepartmentsWhichContaining(String depName){
       return departmentRepository.getListDepartmentsWhichContaining(depName.toUpperCase()).stream()
               .map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }
   @Transactional
   public Integer updateDepartment(String newDepName, Integer id){
       return departmentRepository.updateDepartment(newDepName, id);
   }

   public List<DepartmentReadDto> findAllWithSorting(String sortProps){
       Sort sort = Sort.by(sortProps).and(Sort.by("id")); // sortProps == departmentName or id... and so on
       return departmentRepository.findAllBy(sort).stream().map(dep->DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }
   public List<DepartmentReadDto> findAllWithPageable(Integer pageNumber, Integer pageSize, String sortProps){
       PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortProps));
       return departmentRepository.findAllBy(pageRequest).stream().map(dep -> DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
   }
//   public void findAllByAndWillReturnSlice(){
//       PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("departmentName"));
//       Slice<Department> slice = departmentRepository.findAllBy(pageRequest);
//        slice.forEach(sl -> System.out.println(sl.getDepartmentName()));
//       while (slice.hasNext()){
//            slice = departmentRepository.findAllBy(slice.nextPageable());
//           slice.forEach(sl-> System.out.println(sl.getDepartmentName()));
//       }
//   }
public List<DepartmentReadDto> findAllByAndWillReturnPage(Integer pageNumber, Integer pageSize, String sortProps){
    PageRequest pageRequest = PageRequest.of(pageNumber - 1 , pageSize, Sort.by(sortProps));
    return departmentRepository.findAllBy(pageRequest).map(dep->DepartmentMapper.createResponseDTO(dep)).toList();
}

public List<DepartmentReadDto> findAllWithEntityGraphAndCityRegion(){
       return departmentRepository.findAllWithListEmployees().stream().map(dep-> {
                   DepartmentReadDto responseDTO = DepartmentMapper.createResponseDTO(dep);
                   responseDTO.setListOfEmployees(dep.getListOfEmployees());
                   return responseDTO;
               })
               .collect(Collectors.toList());
}
public List<DepartmentReadDto> findAllWithEntityGraphAndCityRegion_DynamicDefine(){
       return departmentRepository.findAllWithListEmployeesAndCityRegion().stream().map(dep-> {
                   DepartmentReadDto responseDTO = DepartmentMapper.createResponseDTO(dep);
                   responseDTO.setListOfEmployees(dep.getListOfEmployees());
                   return responseDTO;
               })
               .collect(Collectors.toList());
}

public List<DepartmentReadDto> findAllWithQueryHint(){
       return departmentRepository.findAllWithQueryHint().stream().map(dep->DepartmentMapper.createResponseDTO(dep))
               .collect(Collectors.toList());
}

public DepartmentProjection findDepartmentAndMapToProjection(Integer id){
       return departmentRepository.findDepartmentById(id);
}

public DepartmentEmployeeProjection getEmployeeAndDepartmentProjection(String firstName){
       return departmentRepository.getEmployeeAndDepartmentProjection(firstName);
}
public List<DepartmentEmployeeProjection> getDepsAndEmployeesProjection(){
       return departmentRepository.getDepsAndEmployeesProjection();
}






    public DepartmentReadDto getDepartmentById(Integer id) {
        Optional<Department> mayBeDepartment = departmentRepository.findById(id);
        return mayBeDepartment.map(department -> DepartmentMapper.createResponseDTO(department))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostConstruct
    public void postConstruct(){
        log.info("EmployeeService postConstruct...");
    }
    @PreDestroy
    public void preDestroy(){
        log.info("EmployeeService preDestroy...");
    }
}
