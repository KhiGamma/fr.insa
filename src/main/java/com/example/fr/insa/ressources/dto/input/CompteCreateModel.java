package com.example.fr.insa.ressources.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompteCreateModel {
	
	private float soldeCompte;

    private float decouvert;

    private List<Integer> clientIds;
}
