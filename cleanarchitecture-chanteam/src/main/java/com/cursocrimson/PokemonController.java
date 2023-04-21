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

/**
 * PokemonController --- Controller for the Pokemon program.
 * @author CLDEVTEAM
 */
@RestController
@RequestMapping("pokemon")
public class PokemonController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980844469447672162L;

	@Autowired
	private transient PokemonBusiness pokemonBusiness;

	/**
	 * roster --- POST Method. Add pokemon to roster. Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/roster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String roster(@RequestBody PokemonSearch value) throws Exception {

		return pokemonBusiness.addPokemonToRoster(value.getName().toLowerCase());
	}

	/**
	 *getPokemonByName  --- GET Method. Get Pokemon by the name . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPokemonByName(@RequestParam("name") String name) throws Exception {

		return pokemonBusiness.getPokemonFromRoster(name.toLowerCase());
	}

	/**
	 *getPokemonById  --- GET Method. Get Pokemon by the id . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getPokemonById(@PathVariable int id) throws Exception {

		return pokemonBusiness.getPokemonFromRosterById(id);
	}

	/**
	 *getRoster  --- GET Method. Get all Pokemon from Roster . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/roster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getRoster() throws Exception {
		return pokemonBusiness.getAllPokemonFromRoster();
	}
	
	/**
	 *deletePokemonFromRosterById  --- DELETE Method. Delete Pokemon from Roster by the id . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/roster/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deletePokemonFromRosterById(@PathVariable int id) throws Exception {

		return pokemonBusiness.deletePokemonFromRosterById(id);
	}
	
	/**
	 *addPokemonToPartyById  --- POST Method. Add Pokemon to a party by the id . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/party", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addPokemonToPartyById(@RequestBody PokemonSearch value) throws Exception {

		return pokemonBusiness.addPokemonToPartyById(value);
	}
	
	/**
	 *getAllPokemonFromParty  --- GET Method. Get all Pokemon from party . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/party", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAllPokemonFromParty() throws Exception {

		return pokemonBusiness.getAllPokemonFromParty();
	}
	
	/**
	 *deletePokemonFromPartyById  --- DELETE Method. Delete a Pokemon from party . Return a string message json value.
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	@RequestMapping(value = "/party/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deletePokemonFromPartyById(@PathVariable int id) throws Exception {

		return pokemonBusiness.deletePokemonFromPartyById(id);
	}
}
