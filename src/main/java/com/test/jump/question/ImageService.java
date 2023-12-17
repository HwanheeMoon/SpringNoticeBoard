package com.test.jump.question;


import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${upload.directory}")
    private static final String UPLOAD_DIR = "images/";

    public String saveImage(MultipartFile image, String username) throws IOException {
        String fileName = generateFileName(image.getOriginalFilename());
        String filePath = UPLOAD_DIR + username + "/" + fileName;
        Path path = Paths.get("src/main/resources/static/" + filePath);
        Files.createDirectories(path.getParent());
        image.transferTo(path);
        return filePath;
    }

    private String generateFileName(String originalFileName) {
        return "IMG_" + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFileName);
    }
}