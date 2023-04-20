package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Move2{
    @JsonProperty("name")
    String name;
    @JsonProperty("url")
    String url;
}
