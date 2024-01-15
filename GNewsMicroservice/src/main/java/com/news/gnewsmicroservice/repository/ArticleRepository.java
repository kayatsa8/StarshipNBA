package com.news.gnewsmicroservice.repository;

import com.news.gnewsmicroservice.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
}
