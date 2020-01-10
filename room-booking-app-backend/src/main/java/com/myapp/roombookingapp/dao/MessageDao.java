package com.myapp.roombookingapp.dao;

import com.myapp.roombookingapp.entity.Message;
import com.myapp.roombookingapp.entity.User;

import java.util.List;

public interface MessageDao {
    void add(Message message);

    void delete(Message message);

    boolean read(Integer messageId);

    Message findById(Integer messageId);

    List<Message> getAllForUser(Integer userToId);
}
