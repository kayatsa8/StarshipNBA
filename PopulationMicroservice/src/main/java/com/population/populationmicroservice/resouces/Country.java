package com.population.populationmicroservice.resouces;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Countries")
public class Country {

    @Id
    public String id;

    public Integer Rank;
    public String CCA3;
    public String Country;
    public String Capital;
    public String Continent;

    @JsonProperty("2022 Population")
    public Integer Population_2022;
    @JsonProperty("2020 Population")

    public Integer Population_2020;
    @JsonProperty("2015 Population")

    public Integer Population_2015;
    @JsonProperty("2010 Population")
    public Integer Population_2010;
    @JsonProperty("2000 Population")
    public Integer Population_2000;
    @JsonProperty("1990 Population")
    public Integer Population_1990;
    @JsonProperty("1980 Population")
    public Integer Population_1980;
    @JsonProperty("1970 Population")
    public Integer Population_1970;
    @JsonProperty("Area (km²)")
    public Integer Area;
    @JsonProperty("Density (per km²)")
    public Double Density;
    @JsonProperty("Growth Rate")
    public Double Growth;
    @JsonProperty("World Population Percentage")
    public Double World_Population_Percentage;

}
