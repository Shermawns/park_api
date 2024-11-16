package com.shermann.park_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApiConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(
                new Info().
                        title("REST API - Spring Park").
                        version("V1").
                        license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")).
                        contact(new Contact().name("Shermann Barbosa Alcântara").email("shermawns@gmail.com"))
        );
    }
}
