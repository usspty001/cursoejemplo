package com.cursocrimson.apipokemon.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Moves{
	
    @JsonProperty("move")
    Move move;
    
    @JsonProperty("version_group_details")
    ArrayList<VersionGroupDetails> version_group_details;
}
