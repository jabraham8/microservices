package com.example.test.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import springfox.documentation.spring.web.paths.AbstractPathProvider;

/**
 * This is intended to go to some common library so every MS can use it
 * 
 * @author abraham
 *
 */
@Component
public class CustomSwaggerPathProvider extends AbstractPathProvider {

	@Value("${spring.profiles.active}")
	private String profile;
	
	@Value("${spring.application.name}")
	private String appPath;
	
    protected String getDocumentationPath() {
        return "/";
    }

    protected String applicationPath() {

    	/* Swagger configuration so we can show swagger through zuul */
   		return "/" + appPath;

    }
}
