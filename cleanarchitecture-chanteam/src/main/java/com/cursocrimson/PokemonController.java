package com.cursocrimson;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursocrimson.pokemon.business.PokemonBusiness;
import com.cursocrimson.pokemon.models.PokemonSearch;

@RestController
@RequestMapping("pokemon")
public class PokemonController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980844469447672162L;
	
	@Autowired
	private transient PokemonBusiness pokemonBusiness;
	
	
	@RequestMapping(value = "/roster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String roster(@RequestBody PokemonSearch value) {

		return pokemonBusiness.addPokemonToRoster(value.getName().toLowerCase());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPokemonByName(@RequestParam("name") String name) {

		return pokemonBusiness.getPokemonFromRoster(name.toLowerCase());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPokemonById(@PathVariable int id) {

		return pokemonBusiness.getPokemonFromRosterById(id);
	}

}
