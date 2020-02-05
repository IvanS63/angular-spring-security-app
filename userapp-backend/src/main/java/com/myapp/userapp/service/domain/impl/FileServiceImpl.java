package com.myapp.userapp.service.domain.impl;

import static java.lang.String.format;

import com.myapp.userapp.service.domain.FileService;
import com.myapp.userapp.util.Profiling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
@PropertySource("classpath:application.properties")
@Profiling
public class FileServiceImpl implements FileService {
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${application.image.storage.path}")
    private String imagePathOnFrontend;

    @Autowired
    private Environment env;

    private String pathToImageFolder;

    @PostConstruct
    public void init() {
        if (Arrays.stream(env.getActiveProfiles()).noneMatch(profile -> profile.equals("integration-test"))) {
            Path currentRelativePath = Paths.get("");
            pathToImageFolder = currentRelativePath.toAbsolutePath().toString();
            pathToImageFolder = pathToImageFolder.substring(0, pathToImageFolder.lastIndexOf("\\")).concat(imagePathOnFrontend);
            if (pathToImageFolder.isEmpty() || !new File(pathToImageFolder).exists()) {
                String error = "Please set the correct location to /src/assets/images frontend folder";
                log.error(error);
                throw new RuntimeException(error);
            } else {
                log.info(format("Image directory location was set to %s", pathToImageFolder));
            }
        }
    }

    @Override
    public void saveFile(MultipartFile multipartFile) {
        log.debug("Received file: name={} size={}, saving to: {}",
                multipartFile.getOriginalFilename(), multipartFile.getSize(), pathToImageFolder);
        try {
            multipartFile.transferTo(new File(pathToImageFolder + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}
