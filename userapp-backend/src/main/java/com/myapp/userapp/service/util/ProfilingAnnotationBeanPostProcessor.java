package com.myapp.userapp.service.util;

import static java.lang.String.format;

import com.myapp.userapp.util.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link BeanPostProcessor} which creates Proxies of {@link Profiling}-annotated classes
 * which uses logger to output method execution time.
 *
 * @author Ivan_Semenov
 */
@Component
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Value("${application.profiling.enabled:false}")
    private boolean profilingEnabled;

    private Map<String, Class> proxyMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!profilingEnabled) {
            return bean;
        }
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            proxyMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!profilingEnabled) {
            return bean;
        }
        Class beanClass = proxyMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                long startTime = System.currentTimeMillis();
                Object result = method.invoke(bean, args);
                long endTime = System.currentTimeMillis();
                System.out.println(format("Method %s() was executed within %s ms", method.getName(), endTime - startTime));
                return result;
            });
        } else {
            return bean;
        }
    }
}
