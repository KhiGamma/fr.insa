package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 5, max = 5)
	private String codeAgence;

	@NotBlank
	private String nomAgence;

	@NotBlank
	private String adresse;

	@NotBlank
	private String ville;

	@OneToMany(mappedBy = "agence", cascade = CascadeType.REMOVE)
	private List<Client> clients;
}






