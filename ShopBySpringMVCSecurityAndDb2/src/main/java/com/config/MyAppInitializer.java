package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MyAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class[] configFiles = {MyAppConfig.class};

        return configFiles;
    }

    @Override
    protected String[] getServletMappings() {
        String[] mappings = {"/"};
        return mappings;
    }
}
