package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Size(min = 5, max = 5)
	private String codeAgence;

	@NotBlank
	private String nomAgence;

	@NotBlank
	private String adresse;

	@NotBlank
	private String ville;

	@OneToMany(mappedBy = "agence")
	private List<Client> clients;
}






