package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.game_statistics.GameStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameStatisticsRepository extends MongoRepository<GameStatistics, String> {
}
