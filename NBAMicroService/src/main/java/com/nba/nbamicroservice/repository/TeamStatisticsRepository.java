package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.team_statistics.TeamStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamStatisticsRepository extends MongoRepository<TeamStatistics, String> {
}
