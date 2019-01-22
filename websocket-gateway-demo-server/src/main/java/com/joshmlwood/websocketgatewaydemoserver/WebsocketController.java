package com.joshmlwood.websocketgatewaydemoserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketController.class);

    @Value("${server.port}")
    private String instId;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/incoming")
    @SendTo("/topic/outgoing")
    public String incoming(Message message) {
        LOGGER.info(String.format("received message: %s", message));
        return String.format("This is application instance %s responding to your message: \"%s\"", instId, message.getMessage());
    }

    @Scheduled(fixedRate = 10000L)
    public void timed() {
        LOGGER.info("sending timed message");
        simpMessagingTemplate.convertAndSend("/topic/outgoing", String.format("This is application instance %s pushing a message", instId));
    }
}
