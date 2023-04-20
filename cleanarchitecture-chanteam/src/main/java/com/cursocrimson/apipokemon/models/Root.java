package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Root{

    @JsonProperty("name")
    String name;
    @JsonProperty("base_experience")
    int base_experience;

//    @JsonProperty("abilities")
//    ArrayList<Ability> abilities;
//    @JsonProperty("forms")
//    ArrayList<Form> forms;
//    @JsonProperty("game_indices")
//    ArrayList<GameIndex> game_indices;
//    @JsonProperty("height")
//    int height;
//    @JsonProperty("held_items")
//    ArrayList<Object> held_items;
//    @JsonProperty("id")
//    int id;
//    @JsonProperty("is_default")
//    boolean is_default;
//    @JsonProperty("location_area_encounters")
//    String location_area_encounters;
//    @JsonProperty("moves")
//    ArrayList<Move> moves;

//    @JsonProperty("order")
//    int order;
//    @JsonProperty("past_types")
//    ArrayList<Object> past_types;
//    @JsonProperty("species")
//    Species species;
//    @JsonProperty("sprites")
//    Sprites sprites;
//    @JsonProperty("stats")
//    ArrayList<Stat> stats;
//    @JsonProperty("types")
//    ArrayList<Type> types;
//    @JsonProperty("weight")
//    int weight;
}
