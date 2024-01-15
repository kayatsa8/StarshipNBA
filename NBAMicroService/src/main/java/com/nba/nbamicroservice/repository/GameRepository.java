package com.nba.nbamicroservice.repository;

import com.nba.nbamicroservice.model.games.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository  extends MongoRepository<Game, String> {}
