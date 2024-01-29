package com.starship.nbamicroservice.repository;

import com.starship.nbamicroservice.model.games.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository  extends MongoRepository<Game, String> {}
