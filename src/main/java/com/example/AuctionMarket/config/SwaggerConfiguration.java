package com.example.AuctionMarket.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Couple App",
                description = "couple app api명세",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/usr/**"};

        return GroupedOpenApi.builder()
                .group("COUPLE API v1")
                .pathsToMatch(paths)
                .build();
    }
}
