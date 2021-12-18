package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Entity
@Builder
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

	@Min(0)
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
	private String ville;

	@ManyToMany(mappedBy = "clients", cascade = CascadeType.REMOVE)
	private List<Compte> comptes;
}







