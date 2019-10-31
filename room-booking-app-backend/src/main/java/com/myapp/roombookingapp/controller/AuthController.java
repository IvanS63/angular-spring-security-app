package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.dto.LoginRequestDto;
import com.myapp.roombookingapp.entity.Role;
import com.myapp.roombookingapp.entity.User;
import com.myapp.roombookingapp.security.JwtProvider;
import com.myapp.roombookingapp.service.RoleService;
import com.myapp.roombookingapp.service.UserService;
import com.myapp.roombookingapp.util.enums.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller for handling login/logout requests.
 *
 * @author Ivan_Semenov
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder encoder;
    
    

    /**
     * Attempt to authenticate with AuthenticationManager bean.
     * Add authentication object to SecurityContextHolder
     * Generate JWT token, then return JWT to client
     *
     * @param loginRequest login request
     * @return 
     */
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok().body(Arrays.asList(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        
        User user = new User();
        user.setId(10);
        user.setLogin("admin");
        user.setPassword(encoder.encode("12345"));
        
        Set<Role> roles = new HashSet<>(); 
        Role role = new Role();
        role.setId(3);
        role.setName(RoleName.ADMIN);
        roles.add(role);
        
        user.setRoles(roles);
        userService.add(user);

        return ResponseEntity.ok().body(user.getPassword());
    }

}
