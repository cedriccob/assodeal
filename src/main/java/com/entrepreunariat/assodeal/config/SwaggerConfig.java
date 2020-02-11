package com.entrepreunariat.assodeal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .securitySchemes(Arrays.asList(securitySchemes()))
                .useDefaultResponseMessages(false);
    }

    private static ApiKey securitySchemes() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Assodeal, l'unité fait les prix bas")
                .description("\"Liste des API pour Assodeal\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Assodeal", "https://assodeal.com/about/", "contact-assodeal@gmail.com"))
                .build();
    }






}
