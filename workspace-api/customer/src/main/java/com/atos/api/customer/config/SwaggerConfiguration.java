package com.atos.api.customer.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration{
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo("Atos API", 
						"Microsservices", 
						"1.0", 
						"urn:tos", 
						new Contact("Atos", "", "aaa@atos.com"), 
						"Apache 2.0", 
						"http://teste", new ArrayList<VendorExtension>()))
				.tags(new Tag("Customers","Gerencia dados dos Clientes"))
				.consumes(Set.of(MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE))
				.produces(Set.of(MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.atos.api.customer.controller"))
				.paths(PathSelectors.any()).build()
				.pathMapping("/")
				.securityContexts(Collections.singletonList(SecurityContext.builder().securityReferences(globalAuth()).build()));				
		
	}
	
	
	private List<SecurityReference> globalAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "Grants read access");
        authorizationScopes[1] = new AuthorizationScope("write", "Grants write access");
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
	
	

}
