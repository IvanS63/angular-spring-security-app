package com.myapp.userapp.service.util;

import com.myapp.userapp.service.security.JwtProvider;
import com.myapp.userapp.util.GenerateJwtSecret;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

/**
 * Simple {@link BeanPostProcessor} implementation for injecting one of pre-defined values in {@link JwtProvider#jwtSecret}.
 *
 * @author Ivan_Semenov
 */
@Component
public class GenerateJwtSecretAnnotationBeanPostProcessor implements BeanPostProcessor {
    
    private Random random = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)  {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            GenerateJwtSecret annotation = field.getAnnotation(GenerateJwtSecret.class);
            if (annotation != null) {
                if (!field.getType().equals(String.class))
                    throw new IllegalStateException("@GenerateJwtSecret cannot be set above " + field.getType());
                if (Modifier.isFinal(field.getModifiers())) {
                    throw new IllegalStateException("Unable to inject to final fields");
                }
                String[] values = annotation.values();
                String value = values[random.nextInt(values.length)];
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, value);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
