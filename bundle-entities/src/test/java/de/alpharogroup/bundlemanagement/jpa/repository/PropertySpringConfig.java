package de.alpharogroup.bundlemanagement.jpa.repository;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;

@Configuration
public class PropertySpringConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
                throws IOException
    {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(System.getProperties());
        return propertySourcesPlaceholderConfigurer;
    }
}