package com.starship.starshipUI.model.News;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Article{
    public String id;
    public String title;
    public String description;
    public String content;
    public String url;
    public String image;
    public String publishedAt;
    public Source source;

    public Article(@JsonProperty("title") String title,
                   @JsonProperty("description") String description,
                   @JsonProperty("content") String content,
                   @JsonProperty("url") String url,
                   @JsonProperty("image") String image,
                   @JsonProperty("publishedAt") String publishedAt,
                   @JsonProperty("source") Source source) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    //getters and setters
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
    public String getImage() {
        return image;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public String getSourceName() {
        return source.getSourceName();
    }
    public String getSourceUrl() {
        return source.getSourceUrl();
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public void setSourceName(String sourceName) {
        this.source.setSourceName(sourceName);
    }
    public void setSourceUrl(String sourceUrl) {
        this.source.setSourceUrl(sourceUrl);
    }

}
