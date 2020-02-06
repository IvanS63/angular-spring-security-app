package com.myapp.userapp.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for initializing Jwt secret value from a pre-defined list.
 *
 * @author Ivan_Semenov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenerateJwtSecret {
    String[] values() default "";
}
