package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.game_statistics.GameStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameStatisticsRepository extends MongoRepository<GameStatistics, String> {
}
