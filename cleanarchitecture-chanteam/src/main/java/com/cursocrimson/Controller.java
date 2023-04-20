package com.cursocrimson;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursocrimson.apipokemon.business.PokeBusiness;

@RestController
public class Controller implements Serializable {

    private static final long serialVersionUID = -1585214153106593672L;

    @Autowired
    private transient PokeBusiness pokeBusiness;

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public HealthCheckResponse index() {
        return new HealthCheckResponse();
    }

    static class HealthCheckResponse {
        public Boolean success = true;
    }

    @GetMapping(path = "getStringTest", produces = { "application/json", "application/xml" })
    public void getStringTest() {
        pokeBusiness.getPokemonById();
        pokeBusiness.getPokemonByName("psyduck");
    }
}
