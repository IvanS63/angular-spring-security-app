package com.myapp.userapp.service.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileService.
 *
 * @author Ivan_Semenov
 */
public interface FileService {
    void saveFile(MultipartFile multipartFile);
}
