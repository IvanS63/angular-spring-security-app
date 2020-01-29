package com.myapp.userapp.service.util;

import com.myapp.userapp.service.security.JwtProvider;
import com.myapp.userapp.util.GenerateJwtSecret;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

/**
 * Simple {@link BeanPostProcessor} implementation for injecting one of pre-defined values in {@link JwtProvider#jwtSecret}.
 *
 * @author Ivan_Semenov
 */
@Component
public class JwtSecretInitializer implements BeanPostProcessor {
    private Random random = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            GenerateJwtSecret annotation = field.getAnnotation(GenerateJwtSecret.class);
            if (annotation != null) {
                if (!field.getType().equals(String.class))
                    throw new RuntimeException("@GenerateJwtSecret cannot be set above " + field.getType());
                if (Modifier.isFinal(field.getModifiers())) {
                    throw new RuntimeException("Unable to inject to final fields");
                }
                String[] values = annotation.values();
                String value = values[random.nextInt(values.length)];
                try {
                    field.setAccessible(true);
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
