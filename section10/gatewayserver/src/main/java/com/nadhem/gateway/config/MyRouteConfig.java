package com.nadhem.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p
                .path("/api/animals/**")
                .filters(f -> f.circuitBreaker(config ->
                    config.setName("animalCircuitBreaker")
                    .setFallbackUri("forward:/contactAdmin")))
                .uri("lb://ANIMAL1"))
            .build();
    }
}