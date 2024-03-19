package com.example.springsterterdemo.http.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUserCreateEditDTO {
    @NotNull
    private String username;
    @Email(message = "email has been next pattern: @, .com and so on...")
    @NotNull
    private String email;
    @Past(message = "birthDate should be in the past time...")
    @NotNull
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate birthDate;

    private MultipartFile image;
}
