package com.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server productionServer = new Server();
        productionServer.setUrl("https://homol-labsync.ddns.net");
        return new OpenAPI()
                .info(new Info()
                        .title("Labsync API")
                        .version("1.0")
                        .description("API documentation for Labsync application"));
    }
}