package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Animated{
    @JsonProperty("back_default")
    String back_default;
    @JsonProperty("back_female")
    Object back_female;
    @JsonProperty("back_shiny")
    String back_shiny;
    @JsonProperty("back_shiny_female")
    Object back_shiny_female;
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_female")
    Object front_female;
    @JsonProperty("front_shiny")
    String front_shiny;
    @JsonProperty("front_shiny_female")
    Object front_shiny_female;
}
