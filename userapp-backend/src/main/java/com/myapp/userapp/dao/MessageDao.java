package com.myapp.userapp.dao;

import com.myapp.userapp.entity.Message;

import java.util.List;

public interface MessageDao {
    void add(Message message);

    void delete(Message message);

    boolean read(Integer messageId);

    Message findById(Integer messageId);

    List<Message> getAllForUser(Integer userToId);
}
