package com.myapp.roombookingapp.dao.impl;

import com.myapp.roombookingapp.dao.MessageDao;
import com.myapp.roombookingapp.entity.Message;
import com.myapp.roombookingapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    private static final Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);

    private static final String SELECT_MESSAGES_BY_USER_ID_QUERY =
            "select m from Message m where m.userTo.id =:userId";
    private static final String UPDATE_MESSAGE_BY_ID_QUERY =
            "UPDATE message SET read=TRUE WHERE id =:id";

    @PersistenceContext(name = "myapp-persistence-unit")
    private EntityManager entityManager;

    @Override
    public void add(Message message) {
        entityManager.persist(message);
    }

    @Override
    public void delete(Message message) {
        entityManager.remove(message);
    }

    @Override
    public boolean read(Integer messageId) {
        int updatedValues = entityManager
                .createNativeQuery(UPDATE_MESSAGE_BY_ID_QUERY)
                .setParameter("id", messageId)
                .executeUpdate();
        return updatedValues > 0;
    }

    @Override
    public Message findById(Integer messageId) {
        return entityManager.find(Message.class, messageId);
    }

    @Override
    public List<Message> getAllForUser(Integer userToId) {
        TypedQuery<Message> query = entityManager.createQuery(SELECT_MESSAGES_BY_USER_ID_QUERY, Message.class);
        return query.setParameter("userId", userToId)
                .getResultList();

    }
}
