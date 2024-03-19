package com.example.springsterterdemo.entity;

import com.example.springsterterdemo.entity.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_users")
public class MyUser extends BaseEntity<Long> {

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "user_image")
    private String image;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
