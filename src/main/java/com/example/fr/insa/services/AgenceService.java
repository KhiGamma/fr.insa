package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Agence;
import com.example.fr.insa.reposotories.AgenceRepository;
import com.example.fr.insa.ressources.dto.AgenceCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgenceService {

    private static final String AGENCE_NOT_FOUND = "Agence non trouvée avec l'id : %s";

    @Autowired
    public AgenceRepository agenceRepository;

    public List<Agence> getAllAgence() {
        return this.agenceRepository.findAll();
    }

    public Agence getAgenceById(int id) throws FonctionnalProcessException {
        Agence agence =
                agenceRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(AGENCE_NOT_FOUND, id)));
        return agence;
    }

    //@Transactional(rollbackOn = Exception.class)
    public Agence saveAgence(AgenceCreateModel agenceToCreate) throws FonctionnalProcessException {

        validateAgenceModel(agenceToCreate);

        Agence a = Agence.builder()
                .nomAgence(agenceToCreate.getNomAgence())
                .adresse(agenceToCreate.getAdresse())
                .ville(agenceToCreate.getVille())
                .clients(new ArrayList<>())
                .build();

        return this.agenceRepository.save(a);
    }

    public void deleteAgence(int id) {
        this.agenceRepository.deleteById(id);
    }

    private void validateAgenceModel(AgenceCreateModel agenceToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(agenceToCreate == null) {
            ex.getMessages().add("AgenceCreateModel : null");
        }

        if(agenceToCreate.getNomAgence() == null || agenceToCreate.getNomAgence().isBlank()) {
            ex.getMessages().add("nom de l'agence est vide");
        }
        if(agenceToCreate.getAdresse() == null || agenceToCreate.getAdresse().isBlank()) {
            ex.getMessages().add("l'adresse est vide");
        }
        if(agenceToCreate.getVille() == null ||  agenceToCreate.getVille().isBlank()) {
            ex.getMessages().add("la ville est vide");
        }

        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
