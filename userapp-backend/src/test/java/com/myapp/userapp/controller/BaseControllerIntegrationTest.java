package com.myapp.userapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.userapp.config.IntegrationTestConfig;
import com.myapp.userapp.config.WebAppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class for setting {@link MockMvc} and test H2 database.
 */
@ContextConfiguration(classes = {IntegrationTestConfig.class, WebAppConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integration-test")
public abstract class BaseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;
    ObjectMapper mapper;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .dispatchOptions(true).build();
        mapper = new ObjectMapper();
    }
}
