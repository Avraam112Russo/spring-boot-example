package com.example.springsterterdemo.http.REST.RestController;

import com.example.springsterterdemo.http.REST.dto.LoginRestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest")
public class LoginRestController {
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> returnLoginResponse(@RequestParam("name") String name,
                                                 @RequestParam("lastName") String lastName,
                                                 @RequestParam("age") String age
                                                 ){
        return new ResponseEntity<>(LoginRestDto.builder()
                .name(name)
                .lastName(lastName)
                .age(Integer.parseInt(age))
                .build(), HttpStatus.OK);
    }
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(){
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }

}
