package com.nba.nbamicroservice.service;

import com.nba.nbamicroservice.fetcher.Fetcher;
import com.nba.nbamicroservice.model.players.Player;
import com.nba.nbamicroservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends NBAService{

    private PlayerRepository repository;


    public PlayerService(PlayerRepository repo){
        repository = repo;
    }


    public void init() {
        List<Player> y=new Fetcher<Player>(repository, webClientBuilder)
                .fetch("https://api-nba-v1.p.rapidapi.com/players",
                        Player.class, getHeaders(), nbaDataExtractor());
        System.out.println(y);
    }

    public List<Player> findAll(){
        return repository.findAll();
    }
}
