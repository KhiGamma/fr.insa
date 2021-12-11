package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Carte;
import com.example.fr.insa.reposotories.CarteRepository;
import com.example.fr.insa.ressources.dto.CarteCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarteService {

    private static final String CARTE_NOT_FOUND = "Carte non trouvée avec l'id : %s";

    @Autowired
    private CarteRepository carteRepository;

    public List<Carte> getAllCarte() {
        return this.carteRepository.findAll();
    }

    public Carte getCarteById(String id) throws FonctionnalProcessException {
        Carte carte =
                carteRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(CARTE_NOT_FOUND, id)));
        return carte;
    }

    public Carte saveCarte(CarteCreateModel carteToCreate) throws FonctionnalProcessException {

        validateCarteModel(carteToCreate);

        Carte c = Carte.builder()
                .motDePasse(carteToCreate.getMotDePasse())
                .plafond(carteToCreate.getPlafond())
                .build();

        return this.carteRepository.save(c);
    }

    public void deleteCarte(String id) {
        this.carteRepository.deleteById(id);
    }

    private void validateCarteModel(CarteCreateModel carteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(carteToCreate == null) {
            ex.getMessages().add("CarteCreateModel : null");
        }

        if(carteToCreate.getPlafond() < 0) {
            ex.getMessages().add("plafond inférieur à 0");
        }
        if(carteToCreate.getMotDePasse() == null || carteToCreate.getMotDePasse().isBlank()) {
            ex.getMessages().add("le mot de passe est vide");
        }

        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
