package com.starship.nbamicroservice.fetcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starship.nbamicroservice.log.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
public class NBAFetcher<T> {

    private MongoRepository<T, String> repository;


    public NBAFetcher(MongoRepository<T, String> repo){
        repository = repo;
    }

    public void fetch(String url, Class<T> elementType){
        String responseBody = fetchData_webClient(url);
        List<T> list = makeList(responseBody, elementType);
        repository.saveAll(list);
    }

    private String fetchData(String url){
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

    private String fetchData_webClient(String url){
        Log.info("fetching nba data from api");

        WebClient webClient = WebClient.create(url);

        String responseBody = webClient.get()
                .uri("")
                .header("X-RapidAPI-Key", "aa2829de53mshfbbe74cfa945e67p134bb1jsn16c2deb9b8b2")
                .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Log.info("nba data fetched");

        return responseBody;
    }

    private List<T> makeList(String responseBody, Class<T> elementType){
        ObjectMapper mapper = new ObjectMapper();

        List<T> list = new ArrayList<>();

        try{
            JsonNode root = mapper.readTree(responseBody);
            JsonNode res = root.path("response");

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
}
