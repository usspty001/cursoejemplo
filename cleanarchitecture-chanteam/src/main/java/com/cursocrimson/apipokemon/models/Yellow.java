package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Yellow{
    @JsonProperty("back_default")
    String back_default;
    @JsonProperty("back_gray")
    String back_gray;
    @JsonProperty("back_transparent")
    String back_transparent;
    @JsonProperty("front_default")
    String front_default;
    @JsonProperty("front_gray")
    String front_gray;
    @JsonProperty("front_transparent")
    String front_transparent;
}
