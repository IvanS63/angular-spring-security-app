package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.entity.User;
import com.myapp.roombookingapp.service.FileService;
import com.myapp.roombookingapp.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * UserController.
 *
 * @author Ivan_Semenov
 */
@CrossOrigin
@RestController
@RequestMapping("/ui/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    //TODO check it
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('USER')")
    public void edit(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.edit(user);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        userService.remove(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public void upload(@RequestParam("file") MultipartFile file) {
        fileService.saveFile(file);
    }
}
