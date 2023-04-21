package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationV{
    @JsonProperty("black-white")
    BlackWhite blackwhite;
}
