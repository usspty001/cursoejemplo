package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Emerald{
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_shiny")
    String front_shiny;
}
