package com.news.gnewsmicroservice.controller;

import com.news.gnewsmicroservice.fetcher.GNewsFetcher;
import com.news.gnewsmicroservice.log.Log;
import com.news.gnewsmicroservice.model.Article;
import com.news.gnewsmicroservice.service.ArticleService;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class GNewsController {

    private ArticleService service;


    public GNewsController(ArticleService articleService){
        service = articleService;
    }

    @Scheduled(cron = "0 0 8-20 * * ?")
    public void autoImportNews(){
        Log.info("importing articles");
        System.out.println("importing articles");
        service.init();

    }
    @PostConstruct
    public void init(){
        autoImportNews();
    }

    @GetMapping("/top-headlines")
    public List<Article> getTopHeadlines(){
        LocalDateTime fiveHoursAgo = LocalDateTime.now().minusHours(5);
        Log.info("searching for articles since " + fiveHoursAgo);
        return service.findAllByAddedAtAfter(fiveHoursAgo);
    }

    @PostMapping("/top-headlines")
    public void importTopHeadLines(){
        service.init();
    }
}
