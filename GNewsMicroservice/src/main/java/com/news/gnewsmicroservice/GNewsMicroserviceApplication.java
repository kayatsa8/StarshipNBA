package com.news.gnewsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class GNewsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GNewsMicroserviceApplication.class, args);
    }

    //TODO make scheduler


}
