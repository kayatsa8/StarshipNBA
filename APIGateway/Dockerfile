FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S gateway-group && adduser -S gateway-user -G gateway-group

USER gateway-user

WORKDIR /nba

COPY ./target/APIGateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "APIGateway-0.0.1-SNAPSHOT.jar"]