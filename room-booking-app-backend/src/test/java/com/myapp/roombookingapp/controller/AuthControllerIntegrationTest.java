package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.dto.LoginRequestDto;
import com.myapp.roombookingapp.dto.SignUpRequestDto;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Unit tests for {@link AuthController} class.
 */
public class AuthControllerIntegrationTest extends BaseControllerIntegrationTest {

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
                .andExpect(status().isOk());
    }
}
