package com.news.gnewsmicroservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Articles")
public class Article {


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
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    public void SetAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
