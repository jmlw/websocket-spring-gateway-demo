package com.joshmlwood.websocketgatewaydemogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WebsocketGatewayDemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketGatewayDemoGatewayApplication.class, args);
    }

}

