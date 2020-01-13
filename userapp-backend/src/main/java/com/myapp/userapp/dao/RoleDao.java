package com.myapp.userapp.dao;

import com.myapp.userapp.entity.Role;

/**
 * RoleDao.
 *
 * @author Ivan_Semenov
 */
public interface RoleDao {

    Role findByName(String name);
}
