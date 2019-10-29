package com.myapp.roombookingapp.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


/**
 * Represents DispatcherServlet configuration
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Load middle and data-tier application context with beans defined in configuration classes
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DbConfig.class};
    }

    /**
     * Load web application context with beans defined
     * in the {@link WebAppConfig} configuration class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebAppConfig.class};
    }

    /**
     * For correct display russian symbols on JSP view (required for i18n)
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter};
    }
}
