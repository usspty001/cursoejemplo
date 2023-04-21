package com.cursocrimson.apipokemon.business;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.cursocrimson.apipokemon.models.Root;
import com.cursocrimson.apipokemon.services.PokemonApiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("PokemonApiBusiness")
@EnableConfigurationProperties
public class PokemonApiBusiness implements Serializable {
	private static final long serialVersionUID = -1585214153106593672L;

	@Autowired
	private transient PokemonApiService pokeApiService;

	
	/**
	 *getPokemonByName  --- Invoke Api Service to get the Pokemon information based on the name. Return a Root object.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public Root getPokemonByName(String value) throws Exception {
		ObjectMapper om = new ObjectMapper();
		Root root = new Root();
		try {
			String jsonPokemon = pokeApiService.getPokemonInfoByName(value);
			if (jsonPokemon != null || jsonPokemon.length() > 0) {
				 root = om.readValue(jsonPokemon, Root.class);

			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return root;
	}
}
