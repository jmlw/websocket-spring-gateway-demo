package com.joshmlwood.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(p -> p
                // give `ws` protocol higher prescience, but allow standard http fallback
                .path("/websocket/**")
                .uri("lb:ws://client/websocket")
            )
            .route(p -> p
                .path("/websocket/**")
                .uri("lb://client/websocket")
            )
            .build();
    }
}