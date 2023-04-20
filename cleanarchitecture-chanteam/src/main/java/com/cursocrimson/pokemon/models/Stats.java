package com.cursocrimson.pokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Stats{
    @JsonProperty("hp") 
    int hp;
    
    @JsonProperty("attack")  
    int attack;
    
    @JsonProperty("defense") 
    int defense;
    
    @JsonProperty("specialAttack") 
    int specialAttack;
    
    @JsonProperty("specialDefense") 
    int specialDefense;
    
    @JsonProperty("speed") 
    int speed;
}
