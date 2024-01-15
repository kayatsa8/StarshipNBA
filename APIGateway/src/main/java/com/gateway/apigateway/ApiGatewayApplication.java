package com.gateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/nba/**")
                        .uri("lb://ms-nba"))
                .route(p -> p
                        .path("/api/population/**")
                        .uri("lb://ms-population"))
                .route(p -> p
                        .path("/api/news/**")
                        .uri("lb://ms-nba"))
                .build();
    }

}
