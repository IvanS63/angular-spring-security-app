package com.myapp.userapp.controller;

import com.myapp.userapp.service.domain.FileService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ResourceUtils;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Integration tests for {@link ResourceController} class.
 */
@WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
public class ResourceControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Autowired
    private FileService fileService;

    private String resourceDirectoryPath;
    private static final String FILE_NAME = "image.png";

    @Before
    public void init() {
        resourceDirectoryPath = Paths.get("src", "test", "resources").toString().concat("/");
        ReflectionTestUtils.setField(fileService, "pathToImageFolder", resourceDirectoryPath);
    }

    @Test
    public void test1_uploadFile() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", FILE_NAME, MediaType.TEXT_PLAIN_VALUE, new byte[]{1, 2, 3});
        mockMvc.perform(multipart("/api/files/upload").file(file))
                .andDo(print())
                .andExpect(status().isCreated());
        assertNotNull(ResourceUtils.getFile(resourceDirectoryPath + "/" + FILE_NAME));
    }

    @After
    public void tearDown() throws FileNotFoundException {
        File file = ResourceUtils.getFile(resourceDirectoryPath + "/" + FILE_NAME);
        file.delete();
    }
}
