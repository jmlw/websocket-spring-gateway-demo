FROM maven:3-jdk-8-alpine AS build
COPY src /opt/src
COPY pom.xml /opt/src
RUN mvn -f /opt/src/pom.xml clean package

FROM openjdk:8-jre-alpine
COPY --from=build /opt/src/target/websocket-gateway-demo-activemq-0.0.1-SNAPSHOT.jar /opt/app.jar
EXPOSE 61613
ENTRYPOINT ["java","-jar","/opt/app.jar"]
