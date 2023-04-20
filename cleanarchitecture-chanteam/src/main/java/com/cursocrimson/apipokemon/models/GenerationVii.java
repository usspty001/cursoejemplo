package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationVii{
    @JsonProperty("icons")
    Icons icons;

    @JsonProperty("ultra-sun-ultra-moon")
    UltraSunUltraMoon ultrasunultramoon;
}
