package com.starship.gnewsmicroservice;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GNewsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GNewsMicroserviceApplication.class, args);
    }



}
