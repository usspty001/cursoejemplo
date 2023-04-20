package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Other{
    @JsonProperty("dream_world")
    DreamWorld dream_world;

    @JsonProperty("home")
    Home home;

    @JsonProperty("official-artwork")
    OfficialArtwork officialartwork;
}
