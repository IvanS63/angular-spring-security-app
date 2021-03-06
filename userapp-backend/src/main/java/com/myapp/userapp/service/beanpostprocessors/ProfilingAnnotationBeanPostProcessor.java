package com.myapp.userapp.service.beanpostprocessors;

import static java.lang.String.format;

import com.myapp.userapp.util.annotations.Profiling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Implementation of {@link BeanPostProcessor} which creates Proxies of {@link Profiling}-annotated classes
 * which uses logger to output method execution time.
 *
 * @author Ivan_Semenov
 */
@Component
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(ProfilingAnnotationBeanPostProcessor.class);

    private Boolean profilingEnabled;
    private Map<String, Class> proxyMap = new HashMap<>();

    public ProfilingAnnotationBeanPostProcessor() {
        final String property = "application.profiling.enabled";
        Properties properties = new Properties();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
            profilingEnabled = Boolean.valueOf(properties.getProperty(property, "false"));
        } catch (IOException ex) {
            log.error(format("Cannot read %s from property file", property));
            profilingEnabled = false;
        }
    }

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
