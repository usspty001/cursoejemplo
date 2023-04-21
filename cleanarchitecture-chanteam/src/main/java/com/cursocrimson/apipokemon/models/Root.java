package com.cursocrimson.apipokemon.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Root{

    @JsonProperty("name")
    String name;
    
    @JsonProperty("base_experience")
    int base_experience;

    @JsonProperty("abilities")
    ArrayList<Abilities> abilities;
    
    @JsonProperty("forms")
    ArrayList<Forms> forms;
    
    @JsonProperty("game_indices")
    ArrayList<GameIndex> game_indices;
    
    @JsonProperty("stats")
    ArrayList<Stats> stats;
    
    @JsonProperty("types")
    ArrayList<Types> types;
    
    @JsonProperty("past_types")
    ArrayList<Object> past_types;
    
    @JsonProperty("held_items")
    ArrayList<Object> held_items;
    
    @JsonProperty("moves")
    ArrayList<Moves> moves;
    
    @JsonProperty("height")
    int height;
    
    @JsonProperty("id")
    int id;
    
    @JsonProperty("is_default")
    boolean is_default;
    
    @JsonProperty("location_area_encounters")
    String location_area_encounters;
    
    @JsonProperty("order")
    int order;
    
    @JsonProperty("species")
    Species species;
    
    @JsonProperty("sprites")
    Sprites sprites;
    
    @JsonProperty("weight")
    int weight;
}
