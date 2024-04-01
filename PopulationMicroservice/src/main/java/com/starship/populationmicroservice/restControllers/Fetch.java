package com.starship.populationmicroservice.restControllers;
import com.starship.populationmicroservice.resouces.Country;
import com.starship.populationmicroservice.repositories.CountryRep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class Fetch {

    private static Logger logger = LogManager.getLogger(Fetch.class);

    @Autowired
    private CountryRep countryRepository;
    ObjectMapper ob = new ObjectMapper();

    @PostMapping("/countries")
    public List<Country> fetchNewCountries(){
        logger.debug("fetching population data from api");

        try {
            HttpResponse<String> response = Unirest.get("https://world-population3.p.rapidapi.com/continents/Europe?rank=1")
                    .header("X-RapidAPI-Key", "53d915a6e2mshc627482e615fdc3p123088jsnf572eb818185")
                    .header("X-RapidAPI-Host", "world-population3.p.rapidapi.com")
                    .asString();
            logger.debug("data was fetched successfully");

            logger.debug("creating object list from data");
            JsonNode node = ob.readTree(response.getBody());
            List<Country> list=ob.readValue(node.toString(), new TypeReference<>(){});

            logger.debug("saving country list in db");
            countryRepository.saveAll(list);
            return list;

        } catch(Exception e){
            logger.error("Exception:");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return new ArrayList<>();

    }

}
