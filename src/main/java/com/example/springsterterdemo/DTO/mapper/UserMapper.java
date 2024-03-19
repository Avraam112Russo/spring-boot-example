package com.example.springsterterdemo.DTO.mapper;

import com.example.springsterterdemo.entity.MyUser;
import com.example.springsterterdemo.http.DTO.MyUserCreateEditDTO;
import com.example.springsterterdemo.http.DTO.MyUserReadDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class UserMapper {
    public MyUser createUserEntity(MyUserCreateEditDTO createEditDTO){
        MyUser build = MyUser.builder()
                .username(createEditDTO.getUsername())
                .email(createEditDTO.getEmail())
                .birthDate(createEditDTO.getBirthDate())
                .build();
        if (!createEditDTO.getImage().isEmpty()){
            build.setImage(createEditDTO.getImage().getOriginalFilename());
        }
        return build;
    }
    public MyUserReadDTO createUserReadDTO(MyUser myUser){
        return MyUserReadDTO.builder()
                .username(myUser.getUsername())
                .email(myUser.getEmail())
                .birthDate(myUser.getBirthDate())
                .build();
    }
}
