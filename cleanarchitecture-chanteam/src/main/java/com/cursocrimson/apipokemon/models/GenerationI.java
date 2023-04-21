package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationI{
    @JsonProperty("red-blue")
    RedBlue redblue;
    @JsonProperty("yellow")
    Yellow yellow;
}
