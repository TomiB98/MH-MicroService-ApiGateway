package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("user-service", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://user-service"))

                .route("user-service", r -> r.path("/api/auth/**")
                        .uri("lb://user-service"))

                .route("product-service", r -> r.path("/api/product/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://product-service"))

                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("lb://order-service"))
                .build();
    }
}
