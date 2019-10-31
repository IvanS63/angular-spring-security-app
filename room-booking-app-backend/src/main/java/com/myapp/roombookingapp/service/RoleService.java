package com.myapp.roombookingapp.service;

import com.myapp.roombookingapp.entity.Role;

/**
 * RoleService.
 *
 * @author Ivan_Semenov
 */
public interface RoleService {
    Role findByName(String name);
}
