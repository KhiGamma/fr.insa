package com.example.fr.insa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;
	@ManyToOne
	private Agence agence;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@NotBlank
	private int age;
	@NotBlank
	@Size(min = 10, max = 10)
	private String telephone;
	@NotBlank
	private String adresse;
	@NotBlank
	@Size(min = 5, max = 5)
	private String codePostal;
	@NotBlank
	private String Ville;
	
}







