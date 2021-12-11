package com.example.fr.insa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(min = 16, max = 16)
	private String numeroCarte;

	@NotBlank
	private String motDePasse;

	@Min(0)
	private float plafond;

	@ManyToOne
	@JsonIgnore
	private Compte compte;
}










