FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S news-group && adduser -S news-user -G news-group

USER news-user

WORKDIR /news

# this allows the program to write into logs volume
RUN mkdir logs

COPY ./target/GNewsMicroservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "GNewsMicroservice-0.0.1-SNAPSHOT.jar"]