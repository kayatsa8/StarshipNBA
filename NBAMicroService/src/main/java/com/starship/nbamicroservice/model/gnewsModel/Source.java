package com.starship.nbamicroservice.model.gnewsModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {

    private String name;
    private String url;



    @JsonCreator
    public Source(@JsonProperty("name") String name, @JsonProperty("url") String url){
        this.name = name;
        this.url = url;
    }

}
