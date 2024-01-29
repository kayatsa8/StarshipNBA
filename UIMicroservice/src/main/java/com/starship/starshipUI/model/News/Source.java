package com.starship.starshipUI.model.News;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {

    private String sourceName;
    private String sourceUrl;

    public Source(@JsonProperty("name") String sourceName,
                  @JsonProperty("url") String sourceUrl) {
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
    }

}
