package com.starship.nbamicroservice.service;

import com.starship.nbamicroservice.model.players.Player;
import com.starship.nbamicroservice.repository.PlayerRepository;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends NBAService{

    private final PlayerRepository repository;


    public PlayerService(PlayerRepository repo){
        repository = repo;
    }


    public void init() {
        List<Player> y=new Fetcher<>(repository)
                .fetch("https://api-nba-v1.p.rapidapi.com/players",
                        Player.class, getHeaders(), nbaDataExtractor());
        System.out.println(y);
    }

    public List<Player> findAll(){
        return repository.findAll();
    }
}
