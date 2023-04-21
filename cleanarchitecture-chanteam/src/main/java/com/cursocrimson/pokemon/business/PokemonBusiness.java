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
	private Map<Integer, String> queueRosterID = new HashMap<>();

	private final int maxSizeQueueRoster = 10;

	private Map<String, PokemonRoot> queueParty = new HashMap<>();
	private Map<Integer, String> queuePartyID = new HashMap<>();

	private final int maxSizeQueueParty = 6;

	/**
	 * addPokemonToRoster
	 * 
	 * @param String value. Value had the pokemon name to add to Roster
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Get the Pokemon information. If doesn't exist return an error
	 *          message, otherwise add the pokemon to roster
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String addPokemonToRoster(String value) throws Exception {
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
			 throw new Exception(e.getMessage());

		}

		return message;
	}

	/**
	 * getPokemonFromRoster
	 * 
	 * @param String value. Value has the pokemon name to be get.
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Get the Pokemon information from the roster based on the name. If
	 *          doesn't exist return an error message, otherwise get the pokemon
	 *          information from the roster
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String getPokemonFromRoster(String value) throws Exception {
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
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * getPokemonFromRosterById
	 * 
	 * @param int id. id has the pokemon id to be get.
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Get the Pokemon information from the roster based on the id. If
	 *          doesn't exist return an error message, otherwise get the pokemon
	 *          information from the roster
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String getPokemonFromRosterById(int id) throws Exception {
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
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * getAllPokemonFromRoster
	 * 
	 * @param .
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Get all Pokemon information from the roster. If doesn't exist return
	 *          an error message, otherwise get all the pokemon information from the
	 *          roster
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String getAllPokemonFromRoster() throws Exception {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRoster.size() > 0) {
				List<PokemonRoot> listOfPokemons = new ArrayList<PokemonRoot>(queueRoster.values());
				message = om.writeValueAsString(listOfPokemons);
			} else {
				objPokemonError.setError("Roster its empty");
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * deletePokemonFromRosterById
	 * 
	 * @param int id. id has the pokemon id to be deleted..
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Check If the pokemon exist on the Roster if doesn't exist an error
	 *          message its returned, otherwise the pokemon its deleted from the
	 *          roster and the party. Pokemon removed info its returned
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String deletePokemonFromRosterById(int id) throws Exception {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueRosterID.containsKey(id)) {
				String pokemonName = queueRosterID.get(id);
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
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * addPokemonToPartyById
	 * 
	 * @param PokemonSearch value. values has a propertie called name that has the
	 *                      pokemon id
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Check If the pokemon exist on the party if exist an error message
	 *          its returned. Checks if the pokemon exists on the roster if doesnt
	 *          exist an error message its returned, otherwise the pokemon its added
	 *          to the party
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String addPokemonToPartyById(PokemonSearch value) throws Exception {
		String message = "";
		PokemonRoot objPokemonRoot = new PokemonRoot();
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		int id = 0;
		try {

			id = Integer.valueOf(value.getName().toString());
			if (queueRosterID.containsKey(id)) {
				if (queueRoster.containsKey(queueRosterID.get(id))) {
					objPokemonRoot = queueRoster.get(queueRosterID.get(id));
					message=addToParty(objPokemonRoot);
					
				}
			} else {
				objPokemonError.setError(String.format("%1$s : %2$s",
						"The following pokemon id doesnt exist on the roster, cant be added to the party", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * getAllPokemonFromParty
	 * 
	 * @param
	 * @return String message with Json format. Can be a error message or all
	 *         Pokemon information
	 * @apiNote Check If the party has information if doesn't had an error message
	 *          its returned otherwise all pokemon from party are returned
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String getAllPokemonFromParty() throws Exception {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queueParty.size() > 0) {
				List<PokemonRoot> listOfPokemons = new ArrayList<PokemonRoot>(queueParty.values());
				message = om.writeValueAsString(listOfPokemons);
			} else {
				objPokemonError.setError("Party its empty");
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * deletePokemonFromPartyById
	 * 
	 * @param int Id. id has the pokemon id to be deleted..
	 * @return String message with Json format. Can be a error message or all
	 *         Pokemon information
	 * @apiNote Check If the pokemon exist on the party if not an error message its
	 *          returned otherwise the pokemon its removed from the party and the
	 *          pokemon info its returned
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	public String deletePokemonFromPartyById(int id) throws Exception {
		String message = "";
		ObjectMapper om = new ObjectMapper();
		PokemonError objPokemonError = new PokemonError();
		try {

			if (queuePartyID.containsKey(id)) {
				String pokemonName = queuePartyID.get(id);
				message = this.getPokemonFromParty(pokemonName);

				queueParty.remove(pokemonName);
				queuePartyID.remove(id);

			} else {
				objPokemonError.setError(
						String.format("%1$s : %2$s", "The following pokemon id doesnt exist on the party", id));
				message = om.writeValueAsString(objPokemonError);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * getPokemonFromParty
	 * 
	 * @param String value. Value has the pokemon name to be get.
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information
	 * @apiNote Get the Pokemon information from the party based on the name. If
	 *          doesn't exist return an error message, otherwise get the pokemon
	 *          information from the party
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	private String getPokemonFromParty(String value) throws Exception {
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
			throw new Exception(e.getMessage());
		}

		return message;
	}

	/**
	 * getPokemonInformationByName
	 * 
	 * @param String value. Value has the pokemon name to be get.
	 * @return Root object value
	 * @apiNote Invoke the getPokemonByName method to try to get pokemon information
	 *          based on the name from PokemApiBusiness
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	private Root getPokemonInformationByName(String value) throws Exception {
		Root objPokeApiInfo = new Root();
		try {
			objPokeApiInfo = pokemonApiBusiness.getPokemonByName(value);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return objPokeApiInfo;
	}

	/**
	 * setValuesToObject
	 * 
	 * @param Root value. Value has the pokemon Api information.
	 * @return PokemonRoot object value.
	 * @apiNote Map Pokemon information from the API model to CL Pokemon Model
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	private PokemonRoot setValuesToObject(Root value) throws Exception {
		PokemonRoot objPokemonRoot = new PokemonRoot();
		try {
			objPokemonRoot = mapper.map(value, PokemonRoot.class);
			if (objPokemonRoot != null) {
				objPokemonRoot.setImage(this.getPokemonImage(value));
				objPokemonRoot.setStats(this.getPokemonStat(value));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return objPokemonRoot;
	}

	/**
	 * getPokemonImage
	 * 
	 * @param Root value. Value has the pokemon Api information.
	 * @return String value.
	 * @apiNote Get the Pokemon from default image from API Information and return
	 *          this.
	 * @author CLDEVTEAM
	 */
	private String getPokemonImage(Root value) {
		String image = value.getSprites().getFront_default();
		return image;
	}

	/**
	 * getPokemonStat
	 * 
	 * @param Root value. Value has the pokemon Api information.
	 * @return Stats value.
	 * @apiNote Get the Stats Pokemon information from API and set this value to
	 *          Stats CL Pokemon Model.
	 * @author CLDEVTEAM
	 */
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

	/**
	 * addToRoster
	 * 
	 * @param PokemonRoot value. Value has the pokemon information.
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information.
	 * @apiNote Check the current size of the Roster. If the roster doesn't had
	 *          availability an error message its returned. If had availability
	 *          checks if the pokemon exist on roster if exist an error message its
	 *          returned otherwise the pokemon its added to the roster
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	private String addToRoster(PokemonRoot objPokemonRoot) throws Exception {
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
					queueRosterID.put(objPokemonRoot.getId(), objPokemonRoot.getName());
					message = om.writeValueAsString(objPokemonRoot);

				} else {
					objPokemonError.setError(
							String.format("%1$s : %2$s", "Pokemon exist on roster", objPokemonRoot.getName()));
					message = om.writeValueAsString(objPokemonError);
				}

			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return message;
	}

	/**
	 * addToParty
	 * 
	 * @param PokemonRoot value. Value has the pokemon information.
	 * @return String message with Json format. Can be a error message or the
	 *         Pokemon information.
	 * @apiNote Check the current size of the Party. If the party doesn't had
	 *          availability an error message its returned. If had availability
	 *          checks if the pokemon exist on party if exist an error message its
	 *          returned otherwise the pokemon its added to the party
	 * @author CLDEVTEAM
	 * @throws Exception 
	 */
	private String addToParty(PokemonRoot objPokemonRoot) throws Exception {
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
					queuePartyID.put(objPokemonRoot.getId(), objPokemonRoot.getName());
					message = om.writeValueAsString(objPokemonRoot);

				} else {
					objPokemonError
							.setError(String.format("%1$s : %2$s", "Pokemon exist on party", objPokemonRoot.getName()));
					message = om.writeValueAsString(objPokemonError);
				}

			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return message;
	}

}
