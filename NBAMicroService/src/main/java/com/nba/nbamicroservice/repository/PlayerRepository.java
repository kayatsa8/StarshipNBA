package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.players.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
