package com.cursocrimson.apipokemon.business;

import java.io.File;
import java.io.IOException;
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

	public void getPokemonById() {
		ObjectMapper om = new ObjectMapper();
		try {
//            Root root = om.readValue(returnJson(), Root.class);
			String ruta = "D:\\CLEAN_ARCHITECTURE_PROJECT\\cursoejemplo\\cleanarchitecture-chanteam\\src\\main\\resources\\pokemonJson.json";
			Root root = om.readValue(new File(ruta), Root.class);
			if (root != null) {
				System.out.println(root);

				String d = "";
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Root getPokemonByName(String value) {
		ObjectMapper om = new ObjectMapper();
		Root root = new Root();
		try {
			String jsonPokemon = pokeApiService.getPokemonInfoByName(value);
			if (jsonPokemon != null || jsonPokemon.length() > 0) {
				 root = om.readValue(jsonPokemon, Root.class);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return root;
	}
}
