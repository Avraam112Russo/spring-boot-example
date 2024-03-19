package com.example.springsterterdemo.http.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(of = {"username", "email", "birthDate"})
public class MyUserReadDTO {
    private Long id;
    private String username;
    private String email;
    private LocalDate birthDate;

}
