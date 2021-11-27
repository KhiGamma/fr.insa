package com.example.fr.insa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAgence;
	@NotBlank
	private String nomAgence;
	@NotBlank
	private String adresse;
	@NotBlank
	private String ville;
	@NotBlank
	@Size(min = 5, max = 5)
	private String codeAgence;
}






