package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerationIii{
    @JsonProperty("emerald")
    Emerald emerald;
    @JsonProperty("firered-leafgreen")
    FireredLeafgreen fireredleafgreen;
    @JsonProperty("ruby-sapphire")
    RubySapphire rubysapphire;
}
