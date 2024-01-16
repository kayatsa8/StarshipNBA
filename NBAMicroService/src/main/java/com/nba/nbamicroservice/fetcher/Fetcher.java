package com.nba.nbamicroservice.fetcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nba.nbamicroservice.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;



public class Fetcher <T>{

    private MongoRepository<T, String> repository;
    private WebClient.Builder webClientBuilder;


    public Fetcher(MongoRepository<T, String> repo, WebClient.Builder builder){
        repository = repo;
        webClientBuilder = builder;
    }

    // continue
    public List<T> fetch(String url, Class<T> elementType, Map<String, String> headers,
                         Function<JsonNode, JsonNode> dataExtractor){

        String responseBody = fetchData_webClient(url, headers);
        List<T> list = makeList(responseBody, elementType, dataExtractor);

        if(repository != null){
            repository.saveAll(list);
        }

        return list;
    }

    private String fetchData_webClient(String url, Map<String, String> headers){
        Log.info("fetching data from api");

        WebClient webClient = webClientBuilder.baseUrl(url).build();

        String responseBody = webClient.get()
                .uri("")
                .headers(httpHeaders -> httpHeaders.setAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        WebClient webClient = WebClient.create(url);

//        String responseBody = webClient.get()
//                .uri("")
//                .headers(httpHeaders -> httpHeaders.setAll(headers))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

        Log.info("data fetched");

        return responseBody;
    }

    private List<T> makeList(String responseBody, Class<T> elementType, Function<JsonNode, JsonNode> dataExtractor){
        ObjectMapper mapper = new ObjectMapper();

        List<T> list = new ArrayList<>();

        try{
            JsonNode root = mapper.readTree(responseBody);
            JsonNode res = dataExtractor.apply(root);

            list = mapper.readValue(res.traverse(),
                    mapper.getTypeFactory().constructCollectionType(List.class, elementType));
        }
        catch (IOException e) {
            Log.severe("Exception:");
            Log.severe(e.getMessage());
            Log.severe(Arrays.toString(e.getStackTrace()));
        }

        return list;
    }


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    // LEGACY
    /*
     *
     * private String fetchData(String url){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "aa2829de53mshfbbe74cfa945e67p134bb1jsn16c2deb9b8b2");
        headers.set("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if(response.getStatusCode().isError()){
            System.out.println("Problem");
            System.exit(1);
        }

        return response.getBody();
    }
     */

}
