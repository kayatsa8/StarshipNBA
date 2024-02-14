package com.starship.gnewsmicroservice.controller;

import com.starship.gnewsmicroservice.model.Article;
import com.starship.gnewsmicroservice.service.ArticleService;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class GNewsController {

    private ArticleService service;

    private static Logger logger = LogManager.getLogger(GNewsController.class);


    public GNewsController(ArticleService articleService){
        service = articleService;

        logger.info("GNewsController was created successfully");
    }

    @Scheduled(cron = "0 0 8-20 * * ?")
    public void autoImportNews(){
        logger.debug("Scheduled article import");

        service.init();

        logger.debug("Articles imported");
    }
    @PostConstruct
    public void init(){
        logger.info("GNewsController init");
        autoImportNews();
    }

    @GetMapping("/top-headlines")
    public List<Article> getTopHeadlines(){
        LocalDateTime fiveHoursAgo = LocalDateTime.now().minusHours(5);
        logger.debug("searching for articles since " + fiveHoursAgo);
        return service.findAllByAddedAtAfter(fiveHoursAgo);
    }

    @PostMapping("/top-headlines")
    public void importTopHeadLines(){
        logger.debug("manually import top-headlines");
        service.init();
    }


//    @GetMapping("/log")
//    public String logTest(){
//        logger.debug("Debug log message");
//        logger.info("Info log message");
//        logger.error("Error log message");
//        return "good";
//    }
}
