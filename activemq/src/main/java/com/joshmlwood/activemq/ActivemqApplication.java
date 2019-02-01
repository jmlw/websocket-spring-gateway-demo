package com.joshmlwood.activemq;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivemqApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivemqApplication.class);

    @Value("${broker.host}")
    private String brokerHost;

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public BrokerService broker() throws Exception {
        final BrokerService broker = new BrokerService();
        final String uri = String.format("stomp://%s:61613", brokerHost);
        LOGGER.info("Adding connector: {}", uri);
        broker.addConnector(uri);

        broker.setPersistent(false);
        final ManagementContext managementContext = new ManagementContext();
        managementContext.setCreateConnector(true);
        broker.setManagementContext(managementContext);

        return broker;
    }
}

