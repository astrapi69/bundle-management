package de.alpharogroup.bundlemanagement.configuration;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan(basePackages = {"de.alpharogroup.bundlemanagement",
								"de.alpharogroup.bundlemanagement.mapper",
								"de.alpharogroup.bundlemanagement.viewmodel"})
@EntityScan(basePackages = "de.alpharogroup.bundlemanagement.jpa.entity")
@EnableJpaRepositories(basePackages = { "de.alpharogroup.bundlemanagement.jpa.repository" })
@EnableTransactionManagement
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log
public class ApplicationConfiguration implements WebMvcConfigurer
{

	public static final String VERSION_API_1 = "v1";
	public static final String REST_VERSION = "/" + VERSION_API_1;

	ApplicationProperties applicationProperties;

	public static ObjectMapper initialize(final @NonNull ObjectMapper objectMapper)
	{
		SimpleModule module;
		JavaTimeModule javaTimeModule;
		SimpleAbstractTypeResolver resolver;

		module = new SimpleModule("bundles", Version.unknownVersion());
		resolver = new SimpleAbstractTypeResolver();
		module.setAbstractTypes(resolver);
		objectMapper.registerModule(module);

		javaTimeModule = new JavaTimeModule();
		objectMapper.registerModule(javaTimeModule);
		return objectMapper;
	}


	@SuppressWarnings("unused")
	Environment env;

	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedOrigins("*");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		converters.add(createXmlHttpMessageConverter());
		converters.add(newMappingJackson2HttpMessageConverter());
	}

	// @Override
	// public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	// MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	// converter.setObjectMapper(objectMapper());
	// converters.add(converter);
	// }
	private MappingJackson2HttpMessageConverter newMappingJackson2HttpMessageConverter()
	{
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		return mappingJackson2HttpMessageConverter;
	}

	private HttpMessageConverter<Object> createXmlHttpMessageConverter()
	{
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;
	}

	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages/errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public ObjectMapper objectMapper()
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper = initialize(objectMapper);
		return objectMapper;
	}

}