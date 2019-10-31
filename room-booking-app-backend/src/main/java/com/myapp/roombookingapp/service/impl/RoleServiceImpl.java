package com.myapp.roombookingapp.service.impl;

import com.myapp.roombookingapp.dao.RoleDao;
import com.myapp.roombookingapp.entity.Role;
import com.myapp.roombookingapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImpl.
 *
 * @author Ivan_Semenov
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
