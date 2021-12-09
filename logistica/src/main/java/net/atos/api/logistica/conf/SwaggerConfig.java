package net.atos.api.logistica.conf;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig implements WebMvcConfigurer {


	@Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("public")
	              .pathsToMatch("/**")
	              .build();
	  }
	
	  @Bean
	  public GroupedOpenApi adminApi() {
	      return GroupedOpenApi.builder()
	              .group("admin")
	              .pathsToMatch("/**")
	              .build();
	  }
	  
	  @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Ordem de Serviço API")
	              .description("Criação de Orens de serviços")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Ordem e Serviço Documentação")
	              .url("https://www.exemplo/docs"));
	  }

}