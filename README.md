# Spring Cloud Gateway Load Balanced Websocket Demo

This project represents a bare-bones example of how to set up a load balanced websocket server utilizing Spring Boot Starter Websocket, and Spring Cloud Gateway, as well as a Message Broker to facilitate messages between multiple server instances.

The project uses Spring Cloud Netflix Eureka for service discovery, however it is possible to manually specify a hostname and port in `RouteLocationConfiguration.java` rather than the service name for routing HTTP and Websocket requests through the gateway. Specifying instances by host and port like this however may preclude you from being able to effectively loadbalance requests.

The provided message broker is Apache ActiveMQ, however it's possible to swap out the ActiveMQ server for the provided RabbitMQ server using the `rabbit.yml` docker-compose file (`docker-compose -f rabbit.yml up -d`). If using rabbitmq, do not run the ActiveMQ server application provided.

## Project Structure
- `activemq`
    - Websocket broker - enables multiple backend instances to share messages so a user connected to any instance can receive messages from other instances of the application
- `eureka`
    - Spring Cloud Netflix Eureka - provides service discovery for enabled clients. This allows our applications to find each other by their `spring.application.name` property and the port they are configured to run on. With this our services can make requests to discoverable services with `http://my-service-name` rather than needing to know the hostname and port of the service
- `gateway`
    - Spring Cloud Gateway - provides routing and load balancing for HTTP and Websocket connections through a single host and port (http://localhost:8080 as configured), and uses service discovery to route requests by service name.
- `server`
    - Our example application which is serving a websocket endpoint for our client to connect to. This application has two profiles configured, a default profile running on port 8081 and a second profile on port 8082.
- `example.html`
    - Our example "client" to connect to our websocket server.

## Requirements
- Java (8 or later)
- Maven, or use maven wrapper in each nested application project
- Web browser

## Running
### Using Docker Compose and ActiveMQ:
- Run `docker-compose -f activemq.yml up -d` to build and start the containers
  - Access Eureka console via http://localhost:8761 or health via http://localhost:8761/actuator/health
  - Access Gateway and application endpoints through http://localhost:8080, or health via http://localhost:8080/actuator/health
    - NOTE: There is no default route
- open `example.html` in a websocket compatible browser

### Start up servers manually:
- `cd activemq` then `./mvnw spring-boot:run`
- `cd eureka` then `./mvnw spring-boot:run`
- `cd gateway` then `./mvnw spring-boot:run`
- `cd server` then `./mvnw spring-boot:run -Dspring.profiles.active=app1`
- `cd server` then `./mvnw spring-boot:run -Dspring.profiles.active=app2`
- open `example.html` in a websocket compatible browser

During startup be patient as eureka attempts to resolve location and port, as well as health for each of the discovery enabled applications (gateway, server [app1], and server [app2]). This could take some time for the discovery server and clients to begin accepting requests.

I've added a timed method to respond to the same websocket topic which the client is subscribed to to simulate push notifications from the server, either in response to a system action or in response to another user's action.

### Demonstration of the project running
![Websocket demonstration](https://github.com/jmlw/websocket-spring-gateway-demo/raw/master/ws-demo-1.gif)

### Demonstration with second user in another window
![Websocket demonstration with second user](https://github.com/jmlw/websocket-spring-gateway-demo/raw/master/ws-demo-2.gif)
