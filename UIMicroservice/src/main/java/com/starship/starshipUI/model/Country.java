package com.starship.starshipUI.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.flow.component.template.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {

    @Id
    public String id;

    public Integer Rank;

    @Getter
    public String CCA3;
    @Getter
    public String Country;
    public String Capital;
    public String Continent;
    @Getter
    public Integer Population_2022;

    @Getter
    public Integer Population_2020;

    public Integer Population_2015;
    public Integer Population_2010;
    public Integer Population_2000;
    public Integer Population_1990;
    public Integer Population_1980;
    public Integer Population_1970;
    public Integer Area;
    public Double Density;
    public Double Growth;
    public Double World_Population_Percentage;

    @JsonCreator
    public Country(@JsonProperty("id") String id, @JsonProperty("rank") Integer rank,
                   @JsonProperty("CCA3") String CCA3, @JsonProperty("country") String country,
                   @JsonProperty("capital") String capital, @JsonProperty("continent") String continent,
                   @JsonProperty("2022 Population") Integer population_2022,
                   @JsonProperty("2020 Population") Integer population_2020,
                   @JsonProperty("2015 Population") Integer population_2015,
                   @JsonProperty("2010 Population") Integer population_2010,
                   @JsonProperty("2000 Population") Integer population_2000,
                   @JsonProperty("1990 Population") Integer population_1990,
                   @JsonProperty("1980 Population") Integer population_1980,
                   @JsonProperty("1970 Population") Integer population_1970,
                   @JsonProperty("Area (km²)") Integer area,
                   @JsonProperty("Density (per km²)") Double density,
                   @JsonProperty("Growth Rate") Double growth,
                   @JsonProperty("World Population Percentage") Double world_Population_Percentage) {
        this.id = id;
        this.Rank = rank;
        this.CCA3 = CCA3;
        this.Country = country;
        this.Capital = capital;
        this.Continent = continent;
        this.Population_2022 = population_2022;
        this.Population_2020 = population_2020;
        this.Population_2015 = population_2015;
        this.Population_2010 = population_2010;
        this.Population_2000 = population_2000;
        this.Population_1990 = population_1990;
        this.Population_1980 = population_1980;
        this.Population_1970 = population_1970;
        this.Area = area;
        this.Density = density;
        this.Growth = growth;
        this.World_Population_Percentage = world_Population_Percentage;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                ", Rank=" + Rank +
                ", CCA3='" + CCA3 + '\'' +
                ", Country='" + Country + '\'' +
                ", Capital='" + Capital + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Population_2022=" + Population_2022 +
                ", Population_2020=" + Population_2020 +
                ", Population_2015=" + Population_2015 +
                ", Population_2010=" + Population_2010 +
                ", Population_2000=" + Population_2000 +
                ", Population_1990=" + Population_1990 +
                ", Population_1980=" + Population_1980 +
                ", Population_1970=" + Population_1970 +
                ", Area=" + Area +
                ", Density=" + Density +
                ", Growth=" + Growth +
                ", World_Population_Percentage=" + World_Population_Percentage +
                '}';
    }
}
