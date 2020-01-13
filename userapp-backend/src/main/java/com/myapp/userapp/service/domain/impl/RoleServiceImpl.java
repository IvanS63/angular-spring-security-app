package com.myapp.userapp.service.domain.impl;

import com.myapp.userapp.dao.RoleDao;
import com.myapp.userapp.entity.Role;
import com.myapp.userapp.service.domain.RoleService;
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
