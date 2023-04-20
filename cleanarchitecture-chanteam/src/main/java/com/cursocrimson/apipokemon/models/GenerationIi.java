package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationIi{
    @JsonProperty("crystal")
    Crystal crystal;
    @JsonProperty("gold")
    Gold gold;
    @JsonProperty("silver")
    Silver silver;
}
