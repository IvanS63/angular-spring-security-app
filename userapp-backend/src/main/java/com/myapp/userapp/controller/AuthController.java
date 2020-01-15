package com.myapp.userapp.controller;

import com.myapp.userapp.dto.JwtResponseDto;
import com.myapp.userapp.dto.LoginRequestDto;
import com.myapp.userapp.dto.SignUpRequestDto;
import com.myapp.userapp.entity.Role;
import com.myapp.userapp.entity.User;
import com.myapp.userapp.exceptions.UserAlreadyExistsException;
import com.myapp.userapp.service.security.JwtProvider;
import com.myapp.userapp.service.domain.RoleService;
import com.myapp.userapp.service.domain.UserService;
import com.myapp.userapp.util.enums.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.String.format;

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

        return ResponseEntity.ok(new JwtResponseDto(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    //TODO fix 403 after logging with new users
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        if (Objects.nonNull(userService.findByLogin(signUpRequestDto.getLogin()))) {
            throw new UserAlreadyExistsException("User with this login already exists");
        }

        User user = new User();
        user.setLogin(signUpRequestDto.getLogin());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(encoder.encode(signUpRequestDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(RoleName.ROLE_USER.name()));
        user.setRoles(roles);
        userService.add(user);

        return new ResponseEntity(user, HttpStatus.CREATED);
    }


}
