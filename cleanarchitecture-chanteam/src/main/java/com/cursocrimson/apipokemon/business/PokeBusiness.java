package com.cursocrimson.apipokemon.business;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.cursocrimson.apipokemon.models.Root;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("PokeBusiness")
@EnableConfigurationProperties
public class PokeBusiness implements Serializable {
	private static final long serialVersionUID = -1585214153106593672L;

	public void getPokemonById(){
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
}
