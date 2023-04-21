package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Versions{
    @JsonProperty("generation-i")
    GenerationI generationi;

    @JsonProperty("generation-ii")
    GenerationIi generationii;

    @JsonProperty("generation-iii")
    GenerationIii generationiii;

    @JsonProperty("generation-iv")
    GenerationIv generationiv;

    @JsonProperty("generation-v")
    GenerationV generationv;

    @JsonProperty("generation-vi")
    GenerationVi generationvi;
    @JsonProperty("generation-vii")
    GenerationVii generationvii;
    @JsonProperty("generation-viii")
    GenerationViii generationviii;
}
