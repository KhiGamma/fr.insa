package com.example.fr.insa.ressources.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCreateModel {
    private String nom;

    private String prenom;

    private int age;

    private String telephone;

    private String adresse;

    private String codePostal;

    private String ville;

    private String agenceId;
}
