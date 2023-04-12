package org.zerogravitysolutions.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI baseOpenApi(){
        return new OpenAPI().info(new Info()
                .title("Email Service Open API")
                .version("1.0.0")
                .description("API Documentation for Email Service")
                .contact(new Contact()
                        .name("Vullnet")
                        .email("vullnetsahiti2004@gmail.com")
                        .url("https://mail.google.com/mail/u/0/#inbox")));
    }
}
