package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
	@JsonIgnore
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

	@ManyToMany(mappedBy = "clients")
	private List<Compte> comptes;
}







