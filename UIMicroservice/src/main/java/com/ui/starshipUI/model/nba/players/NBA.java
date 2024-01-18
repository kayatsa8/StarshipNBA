package com.ui.starshipUI.model.nba.players;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NBA {

    private int start;
    private int pro;


    @JsonCreator
    public NBA(@JsonProperty("start") int start, @JsonProperty("pro") int pro) {
        this.start = start;
        this.pro = pro;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPro() {
        return pro;
    }

    public void setPro(int pro) {
        this.pro = pro;
    }
}
