package com.myapp.userapp.service.domain;

import com.myapp.userapp.entity.Role;

/**
 * RoleService.
 *
 * @author Ivan_Semenov
 */
public interface RoleService {
    Role findByName(String name);
}
