package com.myapp.userapp.controller;

import com.myapp.userapp.dto.MessageDto;
import com.myapp.userapp.service.domain.MessageService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link MessageController} class.
 */
@WithMockUser(username = "admin", password = "12345", roles = "ADMIN")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void test1_getMessagesForUser() throws Exception {
        this.mockMvc.perform(get("/ui/messages/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void test2_send() throws Exception {
        MessageDto messageDto = new MessageDto();
        messageDto.setUserToId(2);
        messageDto.setUserFromId(1);
        messageDto.setText("Some Text");

        this.mockMvc.perform(post("/ui/messages/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(messageDto)))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(2, messageService.getMessagesForUser(2).size());
    }

    @Test
    public void test3_read() throws Exception {
        this.mockMvc.perform(get("/ui/messages/read/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertTrue(messageService.getMessagesForUser(2).get(1).getRead());
    }

    @Test
    public void test4_delete() throws Exception {
        this.mockMvc.perform(delete("/ui/messages/delete/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(1, messageService.getMessagesForUser(2).size());

    }
}
