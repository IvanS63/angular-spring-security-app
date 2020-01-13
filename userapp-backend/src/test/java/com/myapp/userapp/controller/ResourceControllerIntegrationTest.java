package com.myapp.userapp.controller;

import com.myapp.userapp.service.FileService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Integration tests for {@link ResourceController} class.
 */
@WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
public class ResourceControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Autowired
    private FileService fileService;

    @Test
    public void test1_getAll() throws Exception {
        //TODO add
    }
}
