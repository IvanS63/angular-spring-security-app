package com.myapp.roombookingapp.service;

import com.myapp.roombookingapp.service.security.JwtAuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@PropertySource("classpath:application.properties")
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    @Value("${application.image.storage.path:../}")
    private String storagePath;

    public void saveFile(MultipartFile multipartFile) {
        log.debug("Received file: name={} size={}, saving to: {}",
                multipartFile.getOriginalFilename(), multipartFile.getSize(), storagePath);
        try {
            multipartFile.transferTo(new File(storagePath + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error(e.toString());
        }
    }
}
