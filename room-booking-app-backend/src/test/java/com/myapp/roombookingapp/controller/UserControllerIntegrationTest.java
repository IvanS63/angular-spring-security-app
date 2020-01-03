package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.entity.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for {@link UserController} class.
 */
@WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Test
    public void test1_getAll() throws Exception {
        this.mockMvc.perform(get("/ui/users/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    public void test2_add() throws Exception {
        this.mockMvc.perform(post("/ui/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new User())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test3_delete() throws Exception {
        this.mockMvc.perform(delete("/ui/users/delete/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
