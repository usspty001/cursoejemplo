package com.cursocrimson.pokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonSearch {

	@JsonProperty("id")
	private String name;
}
