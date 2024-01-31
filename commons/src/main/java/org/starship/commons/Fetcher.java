package org.starship.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.starship.nbamicroservice.log.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Fetcher<T>{

    private static Logger logger = LogManager.getLogger(Fetcher.class);

    private final MongoRepository<T, String> repository;

    private WebClient.Builder webClientBuilder;


    public Fetcher(MongoRepository<T, String> repo){
        repository = repo;
        webClientBuilder=null;

        logger.info("creating new fetcher, repository constructor");
    }
    public Fetcher(MongoRepository<T, String> repo, WebClient.Builder builder){
        repository = repo;
        webClientBuilder = builder;

        logger.info("creating new fetcher, repository&builder constructor");
    }

    // continue
    public List<T> fetch(String url, Class<T> elementType, Map<String, String> headers,
                         Function<JsonNode, JsonNode> dataExtractor){

        logger.debug("starting fetch process");
        String responseBody = fetchData_webClient(url, headers);
        logger.debug("data was fetched successfully");

        logger.debug("making object list");
        List<T> list = makeList(responseBody, elementType, dataExtractor);
        logger.debug("object list created");

        if(repository != null){
            repository.saveAll(list);
            logger.info("fetched data saved in repository");
        }

        return list;
    }

    private String fetchData_webClient(String url, Map<String, String> headers){
        logger.debug("fetching data from api: " + url);

        WebClient webClient = WebClient.create(url);

        String responseBody = webClient.get()
                .uri("")
                .headers(httpHeaders -> httpHeaders.setAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        logger.debug("fetched response body: " + responseBody);

        return responseBody;
    }

    private List<T> makeList(String responseBody, Class<T> elementType, Function<JsonNode, JsonNode> dataExtractor){
        ObjectMapper mapper = new ObjectMapper();

        List<T> list = new ArrayList<>();

        try{
            logger.debug("trying to read objects from response body");
            JsonNode root = mapper.readTree(responseBody);
            logger.debug("extracting data");
            JsonNode res = dataExtractor.apply(root);

            logger.debug("making list");
            list = mapper.readValue(res.traverse(),
                    mapper.getTypeFactory().constructCollectionType(List.class, elementType));
        }
        catch (IOException e) {
            logger.error("Exception:");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
        }

        logger.debug("returning object list");
        return list;
    }
    private String fetchData_restTemplate(String url, Map<String, String> headers){
        return null;
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        logger.debug("creating web client");
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
