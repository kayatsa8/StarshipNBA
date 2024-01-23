package com.news.gnewsmicroservice.repository;

import com.news.gnewsmicroservice.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {

    List<Article> findAllByAddedAtAfter(LocalDateTime publishDate);
}
