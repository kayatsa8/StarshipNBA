package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.team_statistics.TeamStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamStatisticsRepository extends MongoRepository<TeamStatistics, String> {
}
