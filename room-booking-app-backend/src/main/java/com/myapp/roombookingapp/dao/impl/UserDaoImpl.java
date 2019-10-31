package com.myapp.roombookingapp.dao.impl;

import static java.lang.String.format;

import com.myapp.roombookingapp.dao.UserDao;
import com.myapp.roombookingapp.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * UserDaoImpl.
 *
 * @author Ivan_Semenov
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(name = "myapp-persistence-unit")
    private EntityManager entityManager;

    private static final String SELECT_USER_BY_LOGIN_QUERY =
            "select * from sys_user where login like '?1'";


    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void merge(User user, boolean isNew) {
        if (isNew) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public void remove(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public User findByLogin(String login) {
        User result = (User) entityManager.createQuery("select u from User u where u.login like :login")
                .setParameter("login", login)
                .getSingleResult();
        return Optional.ofNullable(result)
                .orElseThrow(() -> new IllegalArgumentException(format("No user with login=%s found", login)));
    }


}
