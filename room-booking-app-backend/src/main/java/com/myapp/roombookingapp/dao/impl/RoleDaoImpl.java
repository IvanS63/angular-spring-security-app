package com.myapp.roombookingapp.dao.impl;

import static java.lang.String.format;

import com.myapp.roombookingapp.dao.RoleDao;
import com.myapp.roombookingapp.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * RoleDaoImpl.
 *
 * @author Ivan_Semenov
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(name = "myapp-persistence-unit")
    private EntityManager entityManager;
    
    @Override
    public Role findByName(String name) {
        Role result = (Role) entityManager.createQuery("select r from Role r where r.name like :roleName")
                .setParameter("roleName", name)
                .getSingleResult();
        return Optional.ofNullable(result)
                .orElseThrow(() -> new IllegalArgumentException(format("No role with name=%s found", name)));
    }
}
