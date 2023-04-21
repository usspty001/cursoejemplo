package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Stats{
    @JsonProperty("base_stat")
    int base_stat;
    
    @JsonProperty("effort")
    int effort;
    
    @JsonProperty("stat")
    Stat stat;
}
