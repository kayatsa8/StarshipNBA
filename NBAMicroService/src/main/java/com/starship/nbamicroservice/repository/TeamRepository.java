package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.teams.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
