package com.cursocrimson.cleanarchitecturechanteam;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseBody
    public HealthCheckResponse index() {
        return new HealthCheckResponse();
    }

    static class HealthCheckResponse {
        public Boolean success = true;
    }
}
