package com.example.taxiApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class PassengerDTO {
    private int pasID;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int card;
    private String points;
    private String phone;
}
