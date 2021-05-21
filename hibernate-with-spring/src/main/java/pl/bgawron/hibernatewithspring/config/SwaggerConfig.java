package pl.bgawron.hibernatewithspring.config;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.bgawron.hibernatewithspring"))
                .paths(regex("/employeeApi.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo()
    {
        return new ApiInfo(
                "Spring Boot Swagger Example API",
                "Spring Boot Swagger Example API for demo",
                "1.0",
                "Terms of Service",
                new Contact("SirBarto",
                        "https://github.com/SirBarto/demo-projects/tree/main/hibernate-with-spring",
                        "bartho58@gmail.com"),
                "Apache License Version 2.0",
                "github.com/SirBarto",
                Collections.emptyList()
        );

    }
}