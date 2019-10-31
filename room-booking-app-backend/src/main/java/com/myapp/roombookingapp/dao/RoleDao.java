package com.myapp.roombookingapp.dao;

import com.myapp.roombookingapp.entity.Role;

/**
 * RoleDao.
 *
 * @author Ivan_Semenov
 */
public interface RoleDao {

    Role findByName(String name);
}
