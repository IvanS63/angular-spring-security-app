package com.myapp.roombookingapp.service.domain.impl;

import com.myapp.roombookingapp.dao.MessageDao;
import com.myapp.roombookingapp.entity.Message;
import com.myapp.roombookingapp.service.domain.MessageService;
import com.myapp.roombookingapp.service.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageDao messageDao;

    @Override
    @Transactional
    public boolean send(Integer userFromId, Integer userToId, String text) {
        Message message = new Message();
        message.setUserFrom(userService.findById(userFromId));
        message.setUserTo(userService.findById(userToId));
        message.setText(text);
        message.setDate(new Date());
        messageDao.add(message);
        return true;
    }

    @Override
    @Transactional
    public void delete(Integer messageId) {
        messageDao.delete(messageDao.findById(messageId));
    }

    @Override
    @Transactional
    public Message findById(Integer messageId) {
        return messageDao.findById(messageId);
    }

    @Override
    @Transactional
    public boolean read(Integer messageId) {
        return messageDao.read(messageId);
    }

    @Override
    @Transactional
    public List<Message> getMessagesForUser(Integer userToId) {
        return messageDao.getAllForUser(userToId);
    }
}
