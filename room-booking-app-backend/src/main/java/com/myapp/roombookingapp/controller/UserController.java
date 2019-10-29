package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.entity.User;
import com.myapp.roombookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * UserController.
 *
 * @author Ivan_Semenov
 */
@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/ui/users")
    public ResponseEntity<List<User>> getAll() {
        //return ResponseEntity.ok().body(userService.getAll());
        User user1 = new User();
        User user2 = new User();
        user1.setFirstName("David");
        user1.setLastName("Beckham");
        user2.setFirstName("Cristiano");
        user2.setLastName("Ronaldo");
        return ResponseEntity.ok().body(Arrays.asList(user1, user2));
    }
}
