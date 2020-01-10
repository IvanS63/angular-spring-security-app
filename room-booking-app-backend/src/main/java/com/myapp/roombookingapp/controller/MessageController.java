package com.myapp.roombookingapp.controller;

import com.myapp.roombookingapp.dto.MessageDto;
import com.myapp.roombookingapp.entity.Message;
import com.myapp.roombookingapp.service.domain.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ui/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(messageService.getMessagesForUser(userId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = "/send")
    public void send(@RequestBody MessageDto messageDto) {
        messageService.send(messageDto.getUserFromId(), messageDto.getUserToId(), messageDto.getText());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/read/{id}")
    public boolean read(@PathVariable Integer id) {
        return messageService.read(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        messageService.delete(id);
    }
}
