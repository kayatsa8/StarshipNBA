package com.news.gnewsmicroservice.service;

import com.news.gnewsmicroservice.model.Article;
import com.news.gnewsmicroservice.repository.ArticleRepository;
import org.example.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository repository;


    public ArticleService(ArticleRepository repo){
        repository = repo;
    }

    public void init() {
        new Fetcher<>(repository)
                .fetch("https://gnews.io/api/v4/top-headlines?country=il&category=general" +
                        "&apikey=ca6803dbe05ebcfd71267c4e89437524", Article.class,
                        new HashMap<>(), (root) -> root.path("articles"));
        System.out.println("finished importing articles");
    }

    public List<Article> findAll(){
        return repository.findAll();
    }

    public List<Article> findAllByAddedAtAfter(LocalDateTime publishDate){
        return repository.findAllByAddedAtAfter(publishDate);
    }
}
