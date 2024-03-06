FROM openjdk:19-jdk-alpine

LABEL authors="kayatsa8"

RUN addgroup -S population-group && adduser -S population-user -G population-group

USER population-user

WORKDIR /population

# this allows the program to write into logs volume
RUN mkdir logs

COPY ./target/PopulationMicroservice-0.0.1-SNAPSHOT.jar .

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "PopulationMicroservice-0.0.1-SNAPSHOT.jar"]

# to build: mvn clean install