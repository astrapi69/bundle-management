package de.alpharogroup.bundlemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "de.alpharogroup.bundlemanagement.controller",
        "de.alpharogroup.bundlemanagement.mapper"})
public class TestControllersConfiguration implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {

        final DataSourceTransactionManager txManager = new DataSourceTransactionManager();

        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return ApplicationConfiguration.initialize(new ObjectMapper());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(ApplicationConfiguration.initialize(new ObjectMapper()));
        converters.add(converter);
    }

}
