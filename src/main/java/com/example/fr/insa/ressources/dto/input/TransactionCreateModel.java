package com.example.fr.insa.ressources.dto.input;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCreateModel {

	private String typeTransaction;
	
	private String emetteur;
	
	private String beneficiaire;
	
	private Date date;
	
	private float montantTransaction;
}
