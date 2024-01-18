package com.ui.starshipUI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Fetcher<T> {

    public List<T> fetch(String url, Class<T> elementType, Map<String, String> headers,
                         Function<JsonNode, JsonNode> dataExtractor){

        String responseBody = fetchData_webClient(url, headers);

        List<T> list = makeList(responseBody, elementType, dataExtractor);

        return list;
    }

    private String fetchData_webClient(String url, Map<String, String> headers){
//        Log.info("fetching nba data from api");

        WebClient webClient = WebClient.create(url);

        String responseBody = webClient.get()
                .uri("")
                .headers(httpHeaders -> httpHeaders.setAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        Log.info("nba data fetched");

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
//            Log.severe("Exception:");
//            Log.severe(e.getMessage());
//            Log.severe(Arrays.toString(e.getStackTrace()));
        }

        return list;
    }
}
