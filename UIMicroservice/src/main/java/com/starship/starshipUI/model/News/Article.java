package com.starship.starshipUI.model.News;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article{
    public String id;
    //getters and setters
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

    public void setSourceName(String sourceName) {
        this.source.setSourceName(sourceName);
    }
    public void setSourceUrl(String sourceUrl) {
        this.source.setSourceUrl(sourceUrl);
    }

}
