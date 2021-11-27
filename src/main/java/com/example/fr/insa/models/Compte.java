package com.example.fr.insa.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
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
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String iban;
	private float soldeCompte = 0;
	@NotBlank
	@Min(0)
	private float decouvert;
}









