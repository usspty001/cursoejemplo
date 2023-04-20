package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VersionGroupDetails{
	
    @JsonProperty("level_learned_at")
    int level_learned_at;
    
    @JsonProperty("move_learn_method")
    MoveLearnMethod move_learn_method;
    
    @JsonProperty("version_group")
    VersionGroup version_group;
}
