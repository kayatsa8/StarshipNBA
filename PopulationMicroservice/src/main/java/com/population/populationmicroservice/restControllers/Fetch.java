package com.population.populationmicroservice.restControllers;
import com.population.populationmicroservice.resouces.Country;
import com.population.populationmicroservice.repositories.CountryRep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Fetch {
    @Autowired
    private CountryRep countryRepository;
    ObjectMapper ob = new ObjectMapper();

    @PostMapping("/countries")
    public List<Country> fetchNewCountries(){
        HttpResponse<String> response = Unirest.get("https://world-population3.p.rapidapi.com/continents/Europe?rank=1")
                .header("X-RapidAPI-Key", "53d915a6e2mshc627482e615fdc3p123088jsnf572eb818185")
                .header("X-RapidAPI-Host", "world-population3.p.rapidapi.com")
                .asString();
        try {
            JsonNode node = ob.readTree(response.getBody());
            List<Country> list=ob.readValue(node.toString(), new TypeReference<>(){});
            countryRepository.saveAll(list);
            return list;

        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

}
