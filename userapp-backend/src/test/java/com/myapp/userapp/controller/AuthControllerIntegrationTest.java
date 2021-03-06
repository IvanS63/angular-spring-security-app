package com.myapp.userapp.controller;

import com.myapp.userapp.dto.LoginRequestDto;
import com.myapp.userapp.dto.SignUpRequestDto;
import com.myapp.userapp.entity.User;
import com.myapp.userapp.service.domain.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration tests for {@link AuthController} class.
 */
public class AuthControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_login() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new LoginRequestDto("admin", "12345"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.login").value("admin"))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    public void test_signup() throws Exception {
        this.mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new SignUpRequestDto("newuser", "12345", "test@email.com"))))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value("newuser"));

        User addedUser = userService.findByLogin("newuser");
        userService.remove(addedUser.getId());
    }
}
