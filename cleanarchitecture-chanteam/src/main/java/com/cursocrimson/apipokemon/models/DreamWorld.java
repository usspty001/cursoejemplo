package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DreamWorld{
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_female")
    Object front_female;
}
