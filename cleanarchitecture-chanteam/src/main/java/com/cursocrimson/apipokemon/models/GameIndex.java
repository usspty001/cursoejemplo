package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameIndex{
    @JsonProperty("game_index")
    int game_index;
    @JsonProperty("version")
    Version version;
}
