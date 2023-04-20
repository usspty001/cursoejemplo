package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Type{
    @JsonProperty("slot")
    int slot;
    @JsonProperty("type")
    Type type;
}
