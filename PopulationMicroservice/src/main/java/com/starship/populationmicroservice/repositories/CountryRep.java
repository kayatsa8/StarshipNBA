package com.starship.populationmicroservice.repositories;

import java.util.List;

import com.starship.populationmicroservice.resouces.Country;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRep extends MongoRepository<Country, String> {
    @NonNull
    List<Country> findAll();

}
