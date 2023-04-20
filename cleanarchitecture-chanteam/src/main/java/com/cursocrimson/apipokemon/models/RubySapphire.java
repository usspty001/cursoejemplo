package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RubySapphire{
    @JsonProperty("back_default")
    String back_default;
    @JsonProperty("back_shiny")
    String back_shiny;
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_shiny")
    String front_shiny;
}
