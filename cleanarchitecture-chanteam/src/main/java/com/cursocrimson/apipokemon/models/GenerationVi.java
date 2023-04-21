package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationVi{
    @JsonProperty("omegaruby-alphasapphire")
    OmegarubyAlphasapphire omegarubyalphasapphire;
    @JsonProperty("x-y")
    XY xy;
}
