package com.starship.gnewsmicroservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document("Articles")
public class Article {

    private static Logger logger = LogManager.getLogger(Article.class);


    @JsonIgnore
    private LocalDateTime addedAt = LocalDateTime.now();
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;
    private Source source;


    @JsonCreator
    public Article(@JsonProperty("title") String title, @JsonProperty("description") String description,
                   @JsonProperty("content") String content, @JsonProperty("url") String url,
                   @JsonProperty("image") String image, @JsonProperty("publishedAt") String publishedAt,
                   @JsonProperty("source") Source source) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.source = source;

        logger.debug("new Article created");
        //TODO: ask if toString it to log
    }

}

