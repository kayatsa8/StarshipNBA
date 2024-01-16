package com.nba.nbamicroservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class NBAService {

    @Autowired
    protected WebClient.Builder webClientBuilder;

    protected Function<JsonNode, JsonNode> nbaDataExtractor(){
        return (root) -> root.path("response");
    }

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();

        headers.put("X-RapidAPI-Key", "aa2829de53mshfbbe74cfa945e67p134bb1jsn16c2deb9b8b2");
        headers.put("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");

        return headers;
    }

}
