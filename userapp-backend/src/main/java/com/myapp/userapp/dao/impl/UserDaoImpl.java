package com.myapp.userapp.dao.impl;

import static java.lang.String.format;

import com.myapp.userapp.dao.UserDao;
import com.myapp.userapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * UserDaoImpl.
 *
 * @author Ivan_Semenov
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext(name = "myapp-persistence-unit")
    private EntityManager entityManager;

    private static final String SELECT_USER_BY_LOGIN_QUERY =
            "select * from sys_user where login like '?1'";


    @Override
    public User findById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
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
        List<User> usersByLogin = entityManager.createQuery("select u from User u where u.login like :login")
                .setParameter("login", login)
                .getResultList();
        if (!usersByLogin.isEmpty()) {
            return usersByLogin.get(0);
        } else {
            log.warn("No user with login ={} has been found", login);
            return null;
        }
    }


}
