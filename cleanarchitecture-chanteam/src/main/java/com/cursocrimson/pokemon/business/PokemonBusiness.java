package com.cursocrimson.pokemon.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.cursocrimson.apipokemon.business.PokemonApiBusiness;
import com.cursocrimson.apipokemon.models.Root;
import com.cursocrimson.apipokemon.models.Stat;
import com.cursocrimson.pokemon.helper.Mapper;
import com.cursocrimson.pokemon.models.PokemonError;
import com.cursocrimson.pokemon.models.PokemonRoot;
import com.cursocrimson.pokemon.models.PokemonSearch;
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

	// private final List<PokemonRoot> queueRoster = new ArrayList<>();
	private Map<String, PokemonRoot> queueRoster = new HashMap<>();
	private Map<Integer,String> queueRosterID = new HashMap<>();

	private final int maxSizeQueueRoster = 10;
	
	
	private Map<String, PokemonRoot> queueParty = new HashMap<>();
	private Map<Integer,String> queuePartyID = new HashMap<>();

	private final int maxSizeQueueParty = 6;

	public String addPokemonToRoster(String value) {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {
			objPokemonRoot = this.setValuesToObject(getPokemonInformationByName(value));
			if (objPokemonRoot.getId() > 0) {

				message = addToRoster(objPokemonRoot);
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon cant be found please try again", value));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception

		}

		return message;
	}

	public String getPokemonFromRoster(String value) {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRoster.containsKey(value)) {
				objPokemonRoot = queueRoster.get(value);
				message = om.writeValueAsString(objPokemonRoot);
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon doesnt exist on the roster", value));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	public String getPokemonFromRosterById(int id) {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRosterID.containsKey(id)) {
				message = this.getPokemonFromRoster(queueRosterID.get(id));
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon id doesnt exist on the roster", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	public String getAllPokemonFromRoster()
	{
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRoster.size() >0) {
				List<PokemonRoot> listOfPokemons = new ArrayList<PokemonRoot>(queueRoster.values());
				message = om.writeValueAsString(listOfPokemons);
			} else {
				objPokemonError.setError("Roster its empty");
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	public String deletePokemonFromRosterById(int id) {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRosterID.containsKey(id)) {
				String pokemonName=queueRosterID.get(id);
				message = this.getPokemonFromRoster(pokemonName);
				queueRoster.remove(pokemonName);
				queueRosterID.remove(id);
				
				queueParty.remove(pokemonName);
				queuePartyID.remove(id);
				
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon id doesnt exist on the roster", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	
	public String addPokemonToPartyById(PokemonSearch value) {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		int id=0;
		try {

			id=Integer.valueOf(value.getName().toString());
			if (queueRosterID.containsKey(id)) {
				if (queueRoster.containsKey(queueRosterID.get(id))) {
					objPokemonRoot = queueRoster.get(queueRosterID.get(id));
					addToParty(objPokemonRoot);
					message = om.writeValueAsString(objPokemonRoot);
				} 
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon id doesnt exist on the roster, cant be added to the party", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	public String getAllPokemonFromParty()
	{
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueParty.size() >0) {
				List<PokemonRoot> listOfPokemons = new ArrayList<PokemonRoot>(queueParty.values());
				message = om.writeValueAsString(listOfPokemons);
			} else {
				objPokemonError.setError("Party its empty");
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	public String deletePokemonFromPartyById(int id) {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queuePartyID.containsKey(id)) {
				String pokemonName=queuePartyID.get(id);
				message = this.getPokemonFromParty(pokemonName);
							
				queueParty.remove(pokemonName);
				queuePartyID.remove(id);
				
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon id doesnt exist on the party", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;
	}
	
	private String getPokemonFromParty(String value) {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueParty.containsKey(value)) {
				objPokemonRoot = queueParty.get(value);
				message = om.writeValueAsString(objPokemonRoot);
			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon doesnt exist on the party", value));
				message = om.writeValueAsString(objPokemonError);
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

	private String addToRoster(PokemonRoot objPokemonRoot) {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {
			if (queueRoster.size() >= maxSizeQueueRoster) {
				objPokemonError
						.setError(String.format("%1$s : %2$s", "Max size was reached for rooster", queueRoster.size()));
				message = om.writeValueAsString(objPokemonError);
			} else {
				if (!queueRoster.containsKey(objPokemonRoot.getName())) {
					queueRoster.put(objPokemonRoot.getName(), objPokemonRoot);
					queueRosterID.put(objPokemonRoot.getId(),objPokemonRoot.getName());
					message = om.writeValueAsString(objPokemonRoot);

				} else {
					objPokemonError.setError(
							String.format("%1$s : %2$s", "Pokemon exist on roster", objPokemonRoot.getName()));
					message = om.writeValueAsString(objPokemonError);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}
	
	
	private String addToParty(PokemonRoot objPokemonRoot) {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {
			if (queueParty.size() >= maxSizeQueueParty) {
				objPokemonError
						.setError(String.format("%1$s : %2$s", "Max size was reached for party", queueParty.size()));
				message = om.writeValueAsString(objPokemonError);
			} else {
				if (!queueParty.containsKey(objPokemonRoot.getName())) {
					queueParty.put(objPokemonRoot.getName(), objPokemonRoot);
					queuePartyID.put(objPokemonRoot.getId(),objPokemonRoot.getName());
					message = om.writeValueAsString(objPokemonRoot);

				} else {
					objPokemonError.setError(
							String.format("%1$s : %2$s", "Pokemon exist on party", objPokemonRoot.getName()));
					message = om.writeValueAsString(objPokemonError);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}

}
