package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Compte;
import com.example.fr.insa.reposotories.CompteRepository;
import com.example.fr.insa.ressources.dto.CompteCreateModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService {
	
	private static final String COMPTE_NOT_FOUND = "Compte non trouvée avec l'id : %s";

    @Autowired
    private CompteRepository compteRepository;
    
    public List<Compte> getAllCompte() {
        return this.compteRepository.findAll();
    }

    public Compte getCompteById(int id) throws FonctionnalProcessException {
    	Compte compte =
                compteRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(COMPTE_NOT_FOUND, id)));
        return compte;
    }

    //@Transactional(rollbackOn = Exception.class)
    public Compte saveCompte(CompteCreateModel compteToCreate) throws FonctionnalProcessException {

        validateCompteModel(compteToCreate);

        Compte a = Compte.builder()
                .soldeCompte(compteToCreate.getSoldeCompte())
                .decouvert(compteToCreate.getDecouvert())
                .clients(new ArrayList<>())
                .transactions(new ArrayList<>())
                .cartes(new ArrayList<>())
                .build();

        return this.compteRepository.save(a);
    }

    public void deleteCompte(int id) {
        this.compteRepository.deleteById(id);
    }

    private void validateCompteModel(CompteCreateModel compteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(compteToCreate == null) {
            ex.getMessages().add("CompteCreateModel : null");
        }
        if(compteToCreate.getDecouvert() < 0) {
            ex.getMessages().add("Le découvert est négatif");
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }

}
