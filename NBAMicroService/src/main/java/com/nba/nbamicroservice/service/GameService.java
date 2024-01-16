package com.nba.nbamicroservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.fetcher.NBAFetcher;
import com.nba.nbamicroservice.model.games.Game;
import com.nba.nbamicroservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class GameService extends NBAService{

    private GameRepository repository;


    public GameService(GameRepository repository){
        this.repository = repository;
    }

    public void init(){
        new Fetcher<Game>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/games?date=2023-12-13",
                        Game.class, getHeaders(), nbaDataExtractor());
    }

}
