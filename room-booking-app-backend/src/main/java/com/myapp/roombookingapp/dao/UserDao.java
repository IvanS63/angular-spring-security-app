package com.myapp.roombookingapp.dao;

import com.myapp.roombookingapp.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * UserDao.
 *
 * @author Ivan_Semenov
 */
public interface UserDao {
    User findById(Integer id);

    void add(User user);

    void edit(User user);

    void remove(User user);

    List<User> getAll();

    User findByLogin(String login);
}
