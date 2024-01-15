package com.news.gnewsmicroservice.controller;

import com.news.gnewsmicroservice.log.Log;
import com.news.gnewsmicroservice.model.Article;
import com.news.gnewsmicroservice.repository.ArticleRepository;
import com.news.gnewsmicroservice.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class GNewsController {

    private ArticleService service;


    public GNewsController(ArticleService articleService){
        service = articleService;

//        service.init();
    }

    @GetMapping("/top-headlines")
    public List<Article> getTopHeadlines(){
        Log.info("searching for articles");
        return service.findAll();
    }

}
