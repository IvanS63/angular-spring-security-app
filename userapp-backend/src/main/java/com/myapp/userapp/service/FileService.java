package com.myapp.userapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
@PropertySource("classpath:application.properties")
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    @Value("${application.image.storage.path}")
    private String storagePath;

    @PostConstruct
    public void init() {
        if (storagePath.isEmpty() || !new File(storagePath).exists()) {
            String error = "Please set the correct location to /src/assets/images frontend folder";
            log.error(error);
            throw new RuntimeException(error);
        }
    }

    public void saveFile(MultipartFile multipartFile) {
        log.debug("Received file: name={} size={}, saving to: {}",
                multipartFile.getOriginalFilename(), multipartFile.getSize(), storagePath);
        try {
            multipartFile.transferTo(new File(storagePath + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}
