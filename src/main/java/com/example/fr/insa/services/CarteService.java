package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Carte;
import com.example.fr.insa.models.Compte;
import com.example.fr.insa.reposotories.CarteRepository;
import com.example.fr.insa.ressources.dto.input.CarteCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteService {

    private static final String CARTE_NOT_FOUND = "Carte non trouvée avec l'id : %s";
    private static final String TOO_MUCH_CARTE = "Il y a déjà deux cartes liées au compte d'id : %s";

    @Autowired
    private CarteRepository carteRepository;
    @Autowired
    private CompteService compteService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    public Carte saveCarte(CarteCreateModel carteToCreate) throws Exception {

        validateCarteModel(carteToCreate);

        Compte compte = this.compteService.getCompteById(carteToCreate.getIdCompte());

        Carte c = Carte.builder()
                .numeroCarte(genererNumeroCarte())
                .motDePasse(this.passwordEncoder.encode(carteToCreate.getMotDePasse()))
                .plafond(carteToCreate.getPlafond())
                .compte(compte)
                .build();

        return this.carteRepository.save(c);
    }

    public void deleteCarte(String id) {
        this.carteRepository.deleteById(id);
    }

    private String genererNumeroCarte() {
        Carte existing = null;
        String numeroCarte = "";

        do {
            int un = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
            int deux = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
            int trois = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
            int quatre = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
            numeroCarte = "" + un + deux + trois + quatre;

            existing = this.carteRepository.findByNumeroCarte(numeroCarte);
        } while(existing != null);

        return numeroCarte;
    }

    private void validateCarteModel(CarteCreateModel carteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(carteToCreate == null) {
            ex.getMessages().add("CarteCreateModel : null");
        }

        if(carteToCreate.getPlafond() < 0) {
            ex.getMessages().add("plafond inférieur à 0");
        }
        if(carteToCreate.getMotDePasse() == null || carteToCreate.getMotDePasse().isBlank() || carteToCreate.getMotDePasse().length() != 4) {
            ex.getMessages().add("le mot de passe est incorrecte");
        }
        if (this.compteService.getNombreDeCarte(carteToCreate.getIdCompte()) > 1) {
            ex.getMessages().add(String.format(TOO_MUCH_CARTE, carteToCreate.getIdCompte()));
        }

        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
