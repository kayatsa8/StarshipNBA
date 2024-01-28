package com.starship.starshipUI.model.nba.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Height {

    private String feets;
    private String inches;
    private String meters;


    @JsonCreator
    public Height(@JsonProperty("feets") String feets,
                  @JsonProperty("inches") String inches,
                  @JsonProperty("meters") String meters) {
        this.feets = feets;
        this.inches = inches;
        this.meters = meters;
    }

}
