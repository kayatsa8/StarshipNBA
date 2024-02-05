package com.starship.gnewsmicroservice.service;

import com.starship.gnewsmicroservice.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleService {

    void init();

    List<Article> findAll();

    List<Article> findAllByAddedAtAfter(LocalDateTime publishDate);

}
