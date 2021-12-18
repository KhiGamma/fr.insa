package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

	@Id
	@Size(min = 11, max = 11)
	private String numeroCompte;

	@Size(min = 31, max = 31)
	private String iban;

	private float soldeCompte;

	@Min(0)
	private float decouvert;

	@OneToMany(mappedBy = "compte", cascade = CascadeType.REMOVE)
	private List<Carte> cartes;

	@OneToMany(mappedBy = "emetteur", cascade = CascadeType.REMOVE)
	private List<Transaction> transactions;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "client_compte",
			joinColumns = { @JoinColumn(name = "compte_id") },
			inverseJoinColumns = { @JoinColumn(name = "client_id") })
	private List<Client> clients;
}









