package com.starship.gnewsmicroservice.fetcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starship.gnewsmicroservice.log.Log;
import com.starship.gnewsmicroservice.model.Article;
import com.starship.gnewsmicroservice.repository.ArticleRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GNewsFetcher {

    private final ArticleRepository repository;


    public GNewsFetcher(ArticleRepository repo){
        repository = repo;
    }

    public void fetch(String url){
        String responseBody = fetchData_webClient(url);
        List<Article> list = makeList(responseBody);
        repository.saveAll(list);
    }

    private String fetchData(String url){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if(response.getStatusCode().isError()){
            System.out.println("Problem");
            System.exit(1);
        }

        return response.getBody();
    }

    private String fetchData_webClient(String url){
        Log.info("fetching articles from api");

        WebClient webClient = WebClient.create(url);

        String responseBody = webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Log.info("data fetched");

        return responseBody;
    }

    private List<Article> makeList(String responseBody){
        ObjectMapper mapper = new ObjectMapper();

        List<Article> list = new ArrayList<>();

        try{
            JsonNode root = mapper.readTree(responseBody);
            JsonNode res = root.path("articles");

            list =  mapper.readValue(res.toString(), new TypeReference<>(){});
        }
        catch (JsonProcessingException e) {
            Log.severe("Exception in news:");
            Log.severe(e.getMessage());
            Log.severe(Arrays.toString(e.getStackTrace()));
        }

        return list;
    }



}
