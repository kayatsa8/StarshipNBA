package com.starship.populationmicroservice.restControllers;

import com.starship.populationmicroservice.resouces.Country;
import com.starship.populationmicroservice.repositories.CountryRep;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/population")
public class Countries {

    private static Logger logger = LogManager.getLogger(Countries.class);

    @Autowired
    private CountryRep countryRepository;

    @Autowired
    private MeterRegistry registery;

    @PostConstruct
    public void init(){
        logger.info("init population data");

        if(countryRepository.findAll().isEmpty()){
            logger.debug("importing countries - database is empty");
            //importCountries();
            countryRepository.save(new Country());
        }
        registery.gauge("documents.in.db", Tags.of("collection", "countries"),countryRepository, countryRepository ->countryRepository.findAll().size());
    }

    @PostMapping("/countries")
    public void importCountries(){
        logger.debug("importing countries");
        countryRepository.saveAll(new Fetch().fetchNewCountries());
    }


    @GetMapping("/countries")
    public List<Country> fetchPopulation() {
        logger.debug("returning population data");
        return countryRepository.findAll();
    }
}
