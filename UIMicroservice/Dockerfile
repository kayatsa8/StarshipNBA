FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S ui-group && adduser -S ui-user -G ui-group

USER ui-user

WORKDIR /ui

COPY ./target/UIMicroservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "UIMicroservice-0.0.1-SNAPSHOT.jar"]

# build a vaadin app: mvn clean package -Pproduction