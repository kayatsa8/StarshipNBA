package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.teams.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
