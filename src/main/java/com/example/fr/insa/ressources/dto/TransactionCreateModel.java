package com.example.fr.insa.ressources.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCreateModel {

	private String typeTransaction;
	
	private int emetteur;
	
	private String beneficiaire;
	
	private Date date;
	
	private float montantTransaction;
}
