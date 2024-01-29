package com.starship.nbamicroservice.model.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weight {

    private String pounds;
    private String kilograms;


    @JsonCreator
    public Weight(@JsonProperty("pounds") String pounds, @JsonProperty("kilograms") String kilograms) {
        this.pounds = pounds;
        this.kilograms = kilograms;
    }

}
