package com.cursocrimson;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursocrimson.pokemon.business.PokemonBusiness;

@RestController
public class Controller implements Serializable {

	private static final long serialVersionUID = -1585214153106593672L;

	@Autowired
	private transient PokemonBusiness pokemonBusiness;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public HealthCheckResponse index() {
		return new HealthCheckResponse();
	}

	static class HealthCheckResponse {
		public Boolean success = true;
	}

	@RequestMapping(value = "/getStringTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStringTest() throws Exception {

		return pokemonBusiness.addPokemonToRoster("pikachu");
	}
}
