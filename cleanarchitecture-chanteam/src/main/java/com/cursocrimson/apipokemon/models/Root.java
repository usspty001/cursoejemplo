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

    @JsonProperty("abilities") //YA
    ArrayList<Abilities> abilities;
    
    @JsonProperty("forms") //YA
    ArrayList<Forms> forms;
    
    @JsonProperty("game_indices") //YA
    ArrayList<GameIndex> game_indices;
    
    @JsonProperty("stats") //YA
    ArrayList<Stats> stats;
    
    @JsonProperty("types") //YA
    ArrayList<Types> types;
    
    @JsonProperty("past_types")
    ArrayList<Object> past_types;
    
    @JsonProperty("held_items")
    ArrayList<Object> held_items;
    
    @JsonProperty("moves") //YA
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
