package com.example.fr.insa.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransaction;

	private String typeTransaction;

	@ManyToOne
	@JsonIgnore
	private Compte emetteur;

	@NotBlank
	private String beneficiaire;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Min(0)
	private float montantTransaction;
}










