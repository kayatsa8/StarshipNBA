package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.player_statistics.PlayerStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerStatisticsRepository extends MongoRepository<PlayerStatistics, String> {
}
