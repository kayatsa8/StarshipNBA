package com.starship.starshipUI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Fetcher<T> {

    private static Logger logger = LogManager.getLogger(Fetcher.class);

    public List<T> fetch(String url, Class<T> elementType, Map<String, String> headers,
                         Function<JsonNode, JsonNode> dataExtractor){

        logger.debug("starting fetching process");

        String responseBody = fetchData_webClient(url, headers);

        List<T> list = makeList(responseBody, elementType, dataExtractor);

        logger.debug("returning data");

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

        logger.debug("data fetched");

        return responseBody;
    }

    private List<T> makeList(String responseBody, Class<T> elementType, Function<JsonNode, JsonNode> dataExtractor){
        ObjectMapper mapper = new ObjectMapper();

        List<T> list = new ArrayList<>();

        try{
            logger.debug("reading data from response body");
            JsonNode root = mapper.readTree(responseBody);
            JsonNode res = dataExtractor.apply(root);

            logger.debug("making object list");
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
}
