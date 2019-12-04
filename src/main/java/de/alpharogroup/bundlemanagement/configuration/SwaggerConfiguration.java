package de.alpharogroup.bundlemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage("de.alpharogroup.bundlemanagement"))
			.paths(regex(ApplicationConfiguration.REST_VERSION + "/.*|")).build()
			.apiInfo(metaData());
	}

	private ApiInfo metaData()
	{
		return new ApiInfo("Resourcebundles REST API",
			"REST API for manage several resourcebundles applications",
			ApplicationConfiguration.VERSION_API_1, "",
			new Contact("resourcebundles org.", "www.resourcebundles-applications.com", ""), "", "",
			Collections.emptyList());
	}

}
