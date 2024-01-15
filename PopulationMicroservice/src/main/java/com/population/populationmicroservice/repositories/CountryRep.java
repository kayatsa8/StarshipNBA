package com.population.populationmicroservice.repositories;

import java.util.List;

import com.population.populationmicroservice.resouces.Country;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRep extends MongoRepository<Country, String> {
    @NonNull
    List<Country> findAll();

}
