package com.population.populationmicroservice.restControllers;

import com.population.populationmicroservice.resouces.Country;
import com.population.populationmicroservice.repositories.CountryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/population")
public class Countries {
    @Autowired
    private CountryRep countryRepository;

    @GetMapping("/countries")
    public List<Country> fetchPlayers() {
        return countryRepository.findAll();
    }
}
