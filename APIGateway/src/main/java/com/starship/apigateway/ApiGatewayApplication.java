package com.starship.apigateway;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ApiGatewayApplication {

    private static Logger logger = LogManager.getLogger(ApiGatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
        return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        logger.debug("building routes");

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
