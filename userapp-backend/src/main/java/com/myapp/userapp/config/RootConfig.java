package com.myapp.userapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Project root configuration.
 *
 * @author Ivan_Semenov
 */
@Configuration
@Import({DbConfig.class, SecurityConfig.class, LocaleConfig.class})
public class RootConfig {

    @Bean(name = "filterMultipartResolver")
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
        filterMultipartResolver.setDefaultEncoding("utf-8");
        return filterMultipartResolver;
    }
    
    
}
