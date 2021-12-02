package net.atos.api.notafiscal.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {



  

   @Bean
   public Docket postsApi() {
      return new Docket(DocumentationType.SWAGGER_2)
            
            .consumes(Set.of(MediaType.APPLICATION_JSON_VALUE))
            .produces(Set.of(MediaType.APPLICATION_JSON_VALUE))
            .protocols(Set.of("http","https"))
            .select()
               .paths(postPaths())
               .apis(RequestHandlerSelectors.basePackage("net.atos.api.notafiscal"))
               .paths(PathSelectors.any()).build()
            .pathMapping("/")
            .apiInfo(apiInfo())
            .ignoredParameterTypes(Pageable.class, Sort.class, PagedResourcesAssembler.class)
            .tags(new Tag("Nota Fiscal", "APIs de acesso Nota Fiscal"))
            .forCodeGeneration(true)            
            .useDefaultResponseMessages(false);
   }

   private Predicate<String> postPaths() {
      return regex("/.*");
   }

   private Predicate<String> springBootActuatorJmxPaths() {
      return regex("^/(?!env|restart|pause|resume|refresh).*$");
   }

   private ApiInfo apiInfo() {
      return new ApiInfoBuilder().title("Nota Fiscal API Rest")
            .description("APIs responsáveis por disponibilizar acesso a Nota Fiscal")
            .version("1.0")
            .contact(new Contact("Developer", null,"teste"))
            .build();

   }


   private List<AuthorizationScope> scopes() {
      List<AuthorizationScope> list = new ArrayList<>();
      list.add(new AuthorizationScope("read", "Grants read access"));
      list.add(new AuthorizationScope("write", "Grants write access"));
      return list;
   }

}