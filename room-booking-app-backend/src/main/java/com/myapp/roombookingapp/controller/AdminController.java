package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.entity.User;
import com.myapp.roombookingapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminController.
 *
 * @author Ivan_Semenov
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;


    @GetMapping
    public String init(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/users/edit")
    public String editUser(@ModelAttribute("user") User user) {
        if (user.getId() == null) {
            userService.add(user);
        } else {
            userService.edit(userService.findById(user.getId())); //TODO remove workaround for persisting users with NULLs. This line should not be used
        }
        return "redirect:/adminPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/users/delete/{id}")
    public String removeUser(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        userService.remove(user);
        return "redirect:/adminPage";
    }
    
}
