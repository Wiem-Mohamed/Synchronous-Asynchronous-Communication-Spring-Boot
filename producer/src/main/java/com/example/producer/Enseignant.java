package com.example.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class Enseignant {

    private long id;

    private Long cin;

    private String nom;

    private String prenom;

    private String mail;

    private String grade;
}
