package de.alpharogroup.bundlemanagement.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@ComponentScan(basePackages = "de.alpharogroup.bundlemanagement")
@EntityScan(basePackages = "de.alpharogroup.bundlemanagement.jpa.entity")
@EnableJpaRepositories(basePackages = { "de.alpharogroup.bundlemanagement.jpa.repository" })
@EnableTransactionManagement
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration implements WebMvcConfigurer
{

	public static final String REST_VERSION = "/v1";

	@SuppressWarnings("unused")
	Environment env;


	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedOrigins("*");
	}

	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages/errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}