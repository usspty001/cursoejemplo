package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Abilities{
	
    @JsonProperty("ability")
    Ability ability;
    
    @JsonProperty("is_hidden")
    boolean is_hidden;
    
    @JsonProperty("slot")
    int slot;
}
