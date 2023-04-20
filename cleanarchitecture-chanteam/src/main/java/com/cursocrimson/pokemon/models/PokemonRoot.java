package com.cursocrimson.pokemon.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonRoot{
	
    @JsonProperty("id") 
    int id;
    
    @JsonProperty("name") 
    String name;
    
    @JsonProperty("height") 
    int height;
    
    @JsonProperty("weight") 
    int weight;
    
    @JsonProperty("image") 
    String image;
    
    @JsonProperty("abilities") 
    ArrayList<String> abilities;
    
    @JsonProperty("moves")  
    ArrayList<String> moves;
    
    @JsonProperty("types")
    ArrayList<String> types;
    
    @JsonProperty("stats") 
    Stats stats;
}
