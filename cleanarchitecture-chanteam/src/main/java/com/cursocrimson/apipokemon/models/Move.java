package com.cursocrimson.apipokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Move{
    @JsonProperty("move")
    Move move;
    @JsonProperty("version_group_details")
    ArrayList<VersionGroupDetail> version_group_details;
}
