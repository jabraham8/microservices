package com.example.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	private static final String GROUP_NAME = "test-API";
	private static final String PATH_REGEX = "/test.*";
	private static final String API_TITLE = "Customer Service API";
	private static final String API_DESCRIPTION = "REST API for the Customer Microservice";
	
	@Autowired
	private CustomSwaggerPathProvider pathProvider;

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).groupName(GROUP_NAME).pathProvider(pathProvider)
			.apiInfo(apiInfo()).select().paths(paths()).build();
	}

	private Predicate<String> paths() {
		return Predicates.or(PathSelectors.regex(PATH_REGEX));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(API_TITLE).description(API_DESCRIPTION).build();
	}
}
