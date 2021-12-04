package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String iban;

	private float soldeCompte = 0;

	@NotBlank
	@Min(0)
	private float decouvert;

	@OneToMany(mappedBy = "compte")
	private List<Carte> cartes;

	@OneToMany(mappedBy = "emetteur")
	private List<Transaction> transactions;

	@ManyToMany
	@JoinTable(name = "client_compte",
			joinColumns = { @JoinColumn(name = "compte_id") },
			inverseJoinColumns = { @JoinColumn(name = "client_id") })
	private List<Client> clients;
}









