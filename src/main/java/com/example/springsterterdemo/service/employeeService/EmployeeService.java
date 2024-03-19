package com.example.springsterterdemo.service.employeeService;

import com.example.springsterterdemo.DTO.request.EmployeeCreateEditDto;
import com.example.springsterterdemo.DTO.response.EmployeeReadDto;
import com.example.springsterterdemo.DTO.mapper.EmployeeMapper;
import com.example.springsterterdemo.entity.Employee;
import com.example.springsterterdemo.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeReadDto saveEmployee(EmployeeCreateEditDto requestDto){
        Employee employee = employeeMapper.employeeRequestDtoToJustEmployee(requestDto);
        return employeeMapper.employeeToEmployeeResponseDto(employeeRepository.save(employee));
    }
    public List<EmployeeReadDto> getAllEmployees(){
        return  employeeRepository.findAll().stream()
                .map(employee -> employeeMapper.employeeToEmployeeResponseDto(employee))
                .collect(Collectors.toList());
    }



//    public EmployeeDTO getEntityById(Integer id){
//        Optional<EmployeeDTO> mayBeEmployee = employeeRepository.getEntityById(id);
//       mayBeEmployee.ifPresent(emp -> applicationEventPublisher.publishEvent(new MyEntityEvent(AccessType.READ, emp)));
//        return mayBeEmployee.get();
//    }

    @PostConstruct
    public void postConstruct(){
        log.info("EmployeeService postConstruct...");
    }
    @PreDestroy
    public void preDestroy(){
        log.info("EmployeeService preDestroy...");
    }

}
