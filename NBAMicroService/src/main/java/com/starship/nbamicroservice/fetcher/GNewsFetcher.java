package com.starship.nbamicroservice.fetcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starship.nbamicroservice.log.Log;
import com.starship.nbamicroservice.model.gnewsModel.Article;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class GNewsFetcher {

    public List<Article> fetch(String url){
        String responseBody = fetchData_webClient(url);
        List<Article> list = makeList(responseBody);
        return list;
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
        Log.info("fetching news data from api");

        WebClient webClient = WebClient.create(url);

        String responseBody = webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Log.info("news data fetched");

        return responseBody;
    }

    private List<Article> makeList(String responseBody){
        ObjectMapper mapper = new ObjectMapper();

        List<Article> list = new ArrayList<>();

        try{
            list =  mapper.readValue(responseBody, new TypeReference<List<Article>>(){});
        }
        catch (JsonProcessingException e) {
            Log.severe("Exception:");
            Log.severe(e.getMessage());
            Log.severe(Arrays.toString(e.getStackTrace()));
        }

        return list;
    }



}
