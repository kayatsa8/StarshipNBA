FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S discovery-group && adduser -S discovery-user -G discovery-group

USER discovery-user

WORKDIR /discovery

# this allows the program to write into logs volume
RUN mkdir logs

COPY ./target/ServiceDiscovery-0.0.1-SNAPSHOT.jar .

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "ServiceDiscovery-0.0.1-SNAPSHOT.jar"]