package com.starship.starshipUI.model.News;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {

    private String sourceName;
    private String sourceUrl;

    public Source(@JsonProperty("name") String sourceName,
                  @JsonProperty("url") String sourceUrl) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

}
