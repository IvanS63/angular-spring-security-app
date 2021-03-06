package com.myapp.userapp.dao.impl;

import static java.lang.String.format;

import com.myapp.userapp.dao.RoleDao;
import com.myapp.userapp.entity.Role;
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
        Role result = (Role) entityManager.createNativeQuery("select * from sys_role where name like :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
        return Optional.ofNullable(result)
                .orElseThrow(() -> new IllegalArgumentException(format("No role with name=%s found", name)));
    }
}
