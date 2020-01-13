package com.myapp.userapp.util.enums;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * ROLE_ prefix is required by Spring Security {@link PreAuthorize} hasRole parameter.
 *
 * @author Ivan_Semenov
 */
public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER
}
