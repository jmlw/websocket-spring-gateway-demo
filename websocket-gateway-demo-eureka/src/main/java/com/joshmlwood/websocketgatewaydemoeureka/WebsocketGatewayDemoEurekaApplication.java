package com.joshmlwood.websocketgatewaydemoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WebsocketGatewayDemoEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketGatewayDemoEurekaApplication.class, args);
    }

}

