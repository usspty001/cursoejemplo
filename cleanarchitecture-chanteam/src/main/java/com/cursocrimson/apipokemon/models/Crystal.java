package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Crystal{
    @JsonProperty("back_default")
    String back_default;
    @JsonProperty("back_shiny")
    String back_shiny;
    @JsonProperty("back_shiny_transparent")
    String back_shiny_transparent;
    @JsonProperty("back_transparent")
    String back_transparent;
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_shiny")
    String front_shiny;
    @JsonProperty("front_shiny_transparent")
    String front_shiny_transparent;
    @JsonProperty("front_transparent")
    String front_transparent;
}
