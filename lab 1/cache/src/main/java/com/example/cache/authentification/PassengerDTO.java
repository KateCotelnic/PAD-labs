package com.example.cache.authentification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class PassengerDTO {
    @JsonProperty("pasID")
    private int pasID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("card")
    private int card;
    @JsonProperty("points")
    private String points;
    @JsonProperty("phone")
    private String phone;
}
