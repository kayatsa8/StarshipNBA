package com.population.populationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PopulationMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopulationMicroserviceApplication.class, args);
    }
}
