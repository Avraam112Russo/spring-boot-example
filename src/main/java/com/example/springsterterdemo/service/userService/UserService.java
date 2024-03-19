package com.example.springsterterdemo.service.userService;

import com.example.springsterterdemo.DTO.mapper.UserMapper;
import com.example.springsterterdemo.entity.MyUser;
import com.example.springsterterdemo.entity.UserRole;
import com.example.springsterterdemo.http.DTO.MyUserCreateEditDTO;
import com.example.springsterterdemo.http.DTO.MyUserReadDTO;
import com.example.springsterterdemo.repository.UserRepository;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.mapping.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;

    @Transactional
    public MyUserReadDTO saveNewUser(MyUserCreateEditDTO myUserCreateEditDTO){
        return Optional.of(myUserCreateEditDTO).map((dto) -> {
            uploadImage(dto.getImage());
            return userMapper.createUserEntity(dto);
        }).map(user -> userRepository.save(user))
                .map(user -> userMapper.createUserReadDTO(user))
                .orElseThrow();

    }
    @Transactional(readOnly = true)
    public List<MyUserReadDTO> getAllUsers(){
        return userRepository.findAll()
                .stream().map(myUser -> userMapper.createUserReadDTO(myUser))
                .collect(Collectors.toList());
    }

    public Optional<byte[]> getImage(Long userId){
        return userRepository.findById(userId)
                .map(user -> user.getImage())
                .filter(image -> StringUtils.hasText(image))
                .flatMap(image -> imageService.fetchImage(image));
    }
    public MyUserReadDTO getUserById(Long id){
        return userRepository.findById(id)
                .map(user -> userMapper.createUserReadDTO(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()){
            imageService.saveImage(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), Collections.singleton(UserRole.USER_ROLE)))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Failed to retrieve user: %s", username)));
    }
}
