package com.example.springsterterdemo.entity;

import com.example.springsterterdemo.entity.audit.AuditEntity;
import com.example.springsterterdemo.entity.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.*;


@NamedQuery(name = "Department.testNamedQuery", // in DepartmentRepository we create this method
        query = "select dep from Department dep where lower(dep.departmentName) = lower(:depName) "
)
@NamedEntityGraph(name = "Department.listOfEmployees",
        attributeNodes = {
                @NamedAttributeNode(value = "listOfEmployees"),
                @NamedAttributeNode(value = "cityAndRegion")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "departmentName")
@Entity
//@ToString(exclude = "listOfEmployees")
@Table(name = "t_departments")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED) // doesn't audit embedded fields entity //
public class Department extends AuditEntity<Integer> {

    @Column(name = "department_name", unique = true, nullable = false)
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
    @Builder.Default
    @JsonManagedReference
    @NotAudited
    private List<Employee> listOfEmployees =new ArrayList<>();

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "city_and_region", joinColumns = @JoinColumn(name = "city_and_region_id"))
    @MapKeyColumn(name = "city")
    @Column(name = "region")
    @NotAudited
    private Map<String, Integer> cityAndRegion = new HashMap<>();
    public void addEmployeeToDepartment(Employee... employees){
        Arrays.stream(employees)
                .forEach(emp -> this.listOfEmployees.add(emp));
    }
}
