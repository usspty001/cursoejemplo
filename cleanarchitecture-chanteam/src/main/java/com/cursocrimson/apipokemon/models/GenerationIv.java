package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationIv{
    @JsonProperty("diamond-pearl")
    DiamondPearl diamondpearl;
    @JsonProperty("heartgold-soulsilver")
    HeartgoldSoulsilver heartgoldsoulsilver;
    @JsonProperty("platinum")
    Platinum platinum;
}
