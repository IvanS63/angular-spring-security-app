package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class ResourceController {

    @Autowired
    private FileService fileService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public void upload(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
    }

}
