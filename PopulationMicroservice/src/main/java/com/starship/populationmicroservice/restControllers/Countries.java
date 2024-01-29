package com.starship.populationmicroservice.restControllers;

import com.starship.populationmicroservice.resouces.Country;
import com.starship.populationmicroservice.repositories.CountryRep;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/population")
public class Countries {
    @Autowired
    private CountryRep countryRepository;

    @PostConstruct
    public void init(){
        if(countryRepository.findAll().isEmpty()){
            System.out.println("importing countries - database is empty");
            //importCountries();
            countryRepository.save(new Country());
        }
    }

    @PostMapping("/countries")
    public void importCountries(){
        System.out.println("importing countries");
        countryRepository.saveAll(new Fetch().fetchNewCountries());
    }


    @GetMapping("/countries")
    public List<Country> fetchPlayers() {
        return countryRepository.findAll();
    }
}
