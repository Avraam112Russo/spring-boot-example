package com.example.springsterterdemo.service.userService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService {
    @Value("${app.image.bucket:C:\\Users\\Dmitr\\IdeaProjects\\spring-sterter-demo\\images}")
    private final String BUCKET_NAME;

    @SneakyThrows
    public void saveImage(String imagePath, InputStream content){
        Path filePath = Path.of(BUCKET_NAME, imagePath);
        try (content){
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    @SneakyThrows
    public Optional<byte[]> fetchImage(String imagePath){
        Path filePath = Path.of(BUCKET_NAME, imagePath);
        return Files.exists(filePath) ?
                Optional.of(Files.readAllBytes(filePath)) :
                Optional.empty();
    }
}
