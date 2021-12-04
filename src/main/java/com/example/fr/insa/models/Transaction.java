package com.example.fr.insa.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@ManyToOne
	@JsonIgnore
	private Compte emetteur;

	@NotBlank
	private String beneficiaire;

	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@NotBlank
	private float montantTransaction;
}










