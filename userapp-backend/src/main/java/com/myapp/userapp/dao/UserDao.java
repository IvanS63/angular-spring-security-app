package com.myapp.userapp.dao;

import com.myapp.userapp.entity.User;

import java.util.List;

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
