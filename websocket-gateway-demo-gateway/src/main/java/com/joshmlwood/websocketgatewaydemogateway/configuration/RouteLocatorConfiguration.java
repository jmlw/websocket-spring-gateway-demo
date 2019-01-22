package com.joshmlwood.websocketgatewaydemogateway.configuration;

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
                        .path("/websocket/**")
                        .uri("lb://client/websocket")
                )
                .route(p -> p
                        .path("/websocket/**")
                        .uri("lb:ws://client/websocket")
                )
//                .route(p -> p
//                        .path("/topic/websocket")
//
//                        .uri("lb:ws://client")
//                )
                .route(p -> p
                        .path("/service-instances/**")
                        .uri("lb://client")
                )
                .build();
    }
}
