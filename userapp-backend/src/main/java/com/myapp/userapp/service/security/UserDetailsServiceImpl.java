package com.myapp.userapp.service.security;

import com.myapp.userapp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;

/**
 * Custom implementation for Spring {@link UserDetailsService}.
 *
 * @author Ivan_Semenov
 */
@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(userDao.findByLogin(username))
                .orElseThrow(() -> new UsernameNotFoundException(format("No user with login '%s' was found", username)));
    }
}
