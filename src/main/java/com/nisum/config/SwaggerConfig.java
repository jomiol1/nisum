package com.nisum.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}


	@Bean
	public Docket swaggerApi(TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .produces(getProduces()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        Lists.newArrayList(
                                new ResponseMessageBuilder().code(500).message("500 message")
                                        .responseModel(new ModelRef("Error")).build()))
                .enableUrlTemplating(false);
	}
	
    private Set<String> getProduces() {
        return new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));
    }
    
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	protected ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Nisum Api")
				.description("Challenge Nisum")
				.version("")
				.contact(new Contact("", "", "")).
				extensions(Collections.emptyList()).build();
	}

}
