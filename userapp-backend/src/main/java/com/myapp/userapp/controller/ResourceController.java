package com.myapp.userapp.controller;

import com.myapp.userapp.service.domain.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
