package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.player_statistics.PlayerStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerStatisticsRepository extends MongoRepository<PlayerStatistics, String> {
}
