package com.example.fr.insa.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransaction;
	@NotBlank
	private String typeTransaction;
	@NotBlank
	private String Emetteur;
	@NotBlank
	private String Beneficiaire;
	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@NotBlank
	private float montantTransaction;
}










