package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.entity.User;
import com.myapp.roombookingapp.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable Integer id) {
        userService.remove(id);
    }
}
