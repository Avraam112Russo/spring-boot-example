package com.example.springsterterdemo.http.REST.RestController;

import com.example.springsterterdemo.http.DTO.MyUserCreateEditDTO;
import com.example.springsterterdemo.http.DTO.MyUserReadDTO;
import com.example.springsterterdemo.service.userService.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v7")
public class UserRestController {
    private final UserService userService;
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllUsers(HttpServletResponse response){
        Cookie cookie = new Cookie("token", "token-value");
        cookie.setMaxAge(60 * 60); // 1 hour
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

//    @PostMapping(value = "/user", consumes = MULTIPART_FORM_DATA,
//            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveNewUser(@RequestBody @Validated MyUserCreateEditDTO myUserCreateEditDTO){
        MyUserReadDTO myUserReadDTO = userService.saveNewUser(myUserCreateEditDTO);
        return new ResponseEntity<>(myUserReadDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user/avatar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public byte[] getAvatarImage(@PathVariable("id")Long id){
        return userService.getImage(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
