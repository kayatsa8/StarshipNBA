package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.players.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
