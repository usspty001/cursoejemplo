package com.cursocrimson.pokemon.business;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.cursocrimson.apipokemon.business.PokemonApiBusiness;
import com.cursocrimson.apipokemon.models.Root;
import com.cursocrimson.apipokemon.models.Stat;
import com.cursocrimson.pokemon.helper.Mapper;
import com.cursocrimson.pokemon.models.PokemonRoot;
import com.cursocrimson.pokemon.models.Stats;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("PokemonBusiness")
@EnableConfigurationProperties
public class PokemonBusiness implements Serializable {

	private static final long serialVersionUID = -5396733869590601461L;

	@Autowired
	private transient PokemonApiBusiness pokemonApiBusiness;
	@Autowired
	private transient Mapper mapper;

	public String addPokemonToRoster(String value) {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		try {
			objPokemonRoot = this.setValuesToObject(getPokemonInformationByName(value));
			if(objPokemonRoot.getId()>0)
			{
				message = om.writeValueAsString(objPokemonRoot);

				System.out.print(objPokemonRoot);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}

	private Root getPokemonInformationByName(String value) {
		Root objPokeApiInfo = new Root();
		try {
			objPokeApiInfo = pokemonApiBusiness.getPokemonByName(value);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return objPokeApiInfo;
	}

	private PokemonRoot setValuesToObject(Root value) {
		PokemonRoot objPokemonRoot = new PokemonRoot();
		try {
			objPokemonRoot = mapper.map(value, PokemonRoot.class);
			if (objPokemonRoot != null) {
				objPokemonRoot.setImage(this.getPokemonImage(value));
				objPokemonRoot.setStats(this.getPokemonStat(value));
			}
//			objPokemonRoot.setId(value.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return objPokemonRoot;
	}

	private String getPokemonImage(Root value) {
		return "prueba";
	}

	private Stats getPokemonStat(Root value) {
		Stats objStat = new Stats();

		String nameValue = "";
		int baseStatValue = 0;
		if (!value.getStats().isEmpty()) {
			for (com.cursocrimson.apipokemon.models.Stats statsValue : value.getStats()) {

				baseStatValue = statsValue.getBase_stat();
				nameValue = statsValue.getStat().getName();

				switch (nameValue) {
				case "hp":
					objStat.setHp(baseStatValue);
					break;
				case "attack":
					objStat.setAttack(baseStatValue);
					break;
				case "defense":
					objStat.setDefense(baseStatValue);
					break;
				case "special-attack":
					objStat.setSpecialAttack(baseStatValue);
					break;
				case "special-defense":
					objStat.setSpecialDefense(baseStatValue);
					break;
				case "speed":
					objStat.setSpeed(baseStatValue);
					break;
				default:
					
					break;
				}

			}
		}
		return objStat;
	}
}
