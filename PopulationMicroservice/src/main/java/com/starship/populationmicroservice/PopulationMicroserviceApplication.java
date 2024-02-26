package com.starship.populationmicroservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
public class PopulationMicroserviceApplication {

    private static Logger logger = LogManager.getLogger(PopulationMicroserviceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PopulationMicroserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            Map<String, String> env = System.getenv();

            logger.info("\n-------------------------------------------------------");

            for(String varName : env.keySet()){
                logger.info(varName + ": " + env.get(varName));
            }

            logger.info("-------------------------------------------------------\n");
        };
    }
}
