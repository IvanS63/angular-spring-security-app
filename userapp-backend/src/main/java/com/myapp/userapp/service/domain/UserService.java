package com.myapp.userapp.service.domain;

import com.myapp.userapp.entity.User;

import java.util.List;

/**
 * UserService.
 *
 * @author Ivan_Semenov
 */
public interface UserService {

    void add(User user);

    void edit(User user);

    void remove(Integer id);

    List<User> getAll();

    User findById(Integer id);

    User findByLogin(String login);
}
