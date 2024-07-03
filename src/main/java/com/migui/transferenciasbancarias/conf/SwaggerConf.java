package com.migui.transferenciasbancarias.conf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@OpenAPIDefinition(info = @Info(title = "MIGUI", version = "1.0"), servers = @Server(url = "http://localhost:9001"))
public class SwaggerConf {

    @Bean
    @Order(1)
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/**")
                .packagesToScan("com.web.migui")
                .build();
    }
}

