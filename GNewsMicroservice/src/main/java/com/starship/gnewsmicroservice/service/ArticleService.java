package com.starship.gnewsmicroservice.service;

import com.starship.gnewsmicroservice.model.Article;
import com.starship.gnewsmicroservice.repository.ArticleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.starship.commons.Fetcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleService {

    private static Logger logger = LogManager.getLogger(ArticleService.class);

    private final ArticleRepository repository;


    public ArticleService(ArticleRepository repo){
        repository = repo;

        logger.info("ArticleService was created");
    }

    public void init() {
        logger.debug("ArticleService fetching articles from api");

        new Fetcher<>(repository)
                .fetch("https://gnews.io/api/v4/top-headlines?country=il&category=general" +
                        "&apikey=ca6803dbe05ebcfd71267c4e89437524", Article.class,
                        new HashMap<>(), (root) -> root.path("articles"));

        logger.debug("Articles fetched successfully");
    }

    public List<Article> findAll(){
        logger.debug("ArticleService find all articles");
        return repository.findAll();
    }

    public List<Article> findAllByAddedAtAfter(LocalDateTime publishDate){
        logger.debug("ArticleService find all articles after given date");
        return repository.findAllByAddedAtAfter(publishDate);
    }
}
