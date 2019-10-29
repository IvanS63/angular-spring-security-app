package com.myapp.roombookingapp.dao;

import com.myapp.roombookingapp.entity.User;

import java.util.List;

/**
 * UserDao.
 *
 * @author Ivan_Semenov
 */
public interface UserDao {
    User findById(Integer id);

    void merge(User user, boolean isNew);

    void remove(User user);

    List<User> getAll();

    User findByLogin(String login);
}
