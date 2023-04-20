package com.cursocrimson.apipokemon.services;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cursocrimson.apipokemon.models.Root;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PokemonApiService implements Serializable {

	private static final long serialVersionUID = 2681484394877938651L;

	public Root getPokemonInfoByName2(String value) {
		RestTemplate restTemplate = new RestTemplate();
		Root response = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/pikachu", Root.class);

//                Root root = response.getBody();

		return response;
	}

	public String getPokemonInfoByName(String value) {
		ObjectMapper om = new ObjectMapper();
		
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "https://pokeapi.co/api/v2/pokemon/"+value;
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Application");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> responseJson = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		
		return responseJson.getBody();
	}

}
