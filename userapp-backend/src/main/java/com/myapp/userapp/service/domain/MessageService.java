package com.myapp.userapp.service.domain;

import com.myapp.userapp.entity.Message;

import java.util.List;

public interface MessageService {

    boolean send(Integer userFromId, Integer userToId, String message);

    boolean read(Integer messageId);

    void delete(Integer messageId);

    Message findById(Integer messageId);

    List<Message> getMessagesForUser(Integer userToId);
}
