package com.starship.gnewsmicroservice.repository;

import com.starship.gnewsmicroservice.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {

    List<Article> findAllByAddedAtAfter(LocalDateTime publishDate);
}
