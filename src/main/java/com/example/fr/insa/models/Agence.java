package com.example.fr.insa.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codeAgenceGenerator")
	@SequenceGenerator(name = "codeAgenceGenerator", initialValue = 10004, allocationSize = 1)
	@Max(99999)
	private int codeAgence;

	@NotBlank
	private String nomAgence;

	@NotBlank
	private String adresse;

	@NotBlank
	private String ville;

	@OneToMany(mappedBy = "agence", cascade = CascadeType.REMOVE)
	private List<Client> clients;
}






