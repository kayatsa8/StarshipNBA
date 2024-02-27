FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S nba-group && adduser -S nba-user -G nba-group

USER nba-user

WORKDIR /nba

COPY ./target/NBAMicroService-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "NBAMicroService-0.0.1-SNAPSHOT.jar"]