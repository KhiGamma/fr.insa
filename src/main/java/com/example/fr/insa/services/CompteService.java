package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Agence;
import com.example.fr.insa.models.Client;
import com.example.fr.insa.models.Compte;
import com.example.fr.insa.models.Transaction;
import com.example.fr.insa.reposotories.CompteRepository;
import com.example.fr.insa.ressources.CommonResource;
import com.example.fr.insa.ressources.dto.input.CompteCreateModel;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteService extends CommonResource {
	
	private static final String COMPTE_NOT_FOUND = "Compte non trouvée avec l'id : %s";
    private static final String COMPTE_NOT_FOUND_IBAN = "Compte non trouvée avec l'iban : %s";
    private static final String NOT_SAME_AGENCE = "Le compte d'id %s ne fait pas partie de la même agence";

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClientService clientService;
    
    public List<Compte> getAllCompte() {
        return this.compteRepository.findAll();
    }

    public Compte getCompteById(String id) throws FonctionnalProcessException {
    	Compte compte =
                compteRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(COMPTE_NOT_FOUND, id)));
        return compte;
    }

    public Compte getCompteByIban(String iban) throws FonctionnalProcessException {
        Compte compte =
                compteRepository
                        .findByIban(iban)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(COMPTE_NOT_FOUND_IBAN, iban)));
        return compte;
    }

    public Compte saveCompte(CompteCreateModel compteToCreate) throws Exception {

        validateCompteModel(compteToCreate);

        String numeroCompte = genererNumeroCompte();

        Compte c = Compte.builder()
                .numeroCompte(numeroCompte)
                .iban(genererIban(numeroCompte, compteToCreate.getClientIds().get(0)))
                .soldeCompte(compteToCreate.getSoldeCompte())
                .decouvert(compteToCreate.getDecouvert())
                .clients(new ArrayList<>())
                .transactions(new ArrayList<>())
                .cartes(new ArrayList<>())
                .build();

        // affectation des clients titulaires
        for (Integer id : compteToCreate.getClientIds()) {
            Client client = this.clientService.getClientById(id);

            c.getClients().add(client);
        }

        return this.compteRepository.save(c);
    }

    public List<Transaction> getTransactionOfCompte(String id) throws FonctionnalProcessException {

        Compte compte =
                compteRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(COMPTE_NOT_FOUND, id)));

        return  this.transactionService.getTransactionOfcompte(id);
    }

    public void deleteCompte(String id) {
        this.compteRepository.deleteById(id);
    }

    public String genererNumeroCompte() {
        Compte existing = null;
        String numeroCompte = "";

        do {
            int numero1 = (int) (Math.random() * (99999 - 10000 + 1) + 10000);
            int numero2 = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
            numeroCompte = "" + numero1 + numero2;

            existing = this.compteRepository.findByNumeroCompte(numeroCompte);
        } while(existing != null);

        return numeroCompte;
    }

    public String genererIban(String numeroCompte, int clientId) throws Exception {
        String codeBanque = "30076";
        String iban = "FR76" + codeBanque;
        String codeAgence = this.clientService.getClientById(clientId).getAgence().getCodeAgence();

        String cleRib = String.valueOf(
                97 - ((
                    89L * Integer.parseInt(codeBanque) +
                    15L * Integer.parseInt(codeAgence) +
                    3L  * Long.parseLong(numeroCompte)
                ) % 97));

        iban += codeAgence + numeroCompte + cleRib;

        return iban;
    }

    public void retirerSoldeCompte(String numeroCompte, float montant) throws FonctionnalProcessException {
        Compte compte = this.getCompteById(numeroCompte);

        compte.setSoldeCompte(compte.getSoldeCompte() - montant);

        this.compteRepository.save(compte);
    }

    public void ajouterSoldecompte(String iban, float montant) throws FonctionnalProcessException {
        Compte compte = this.getCompteByIban(iban);

        compte.setSoldeCompte(compte.getSoldeCompte() + montant);

        this.compteRepository.save(compte);
    }

    public int getNombreDeCarte(String id) {
        return this.compteRepository.nombreDeCarte(id);
    }

    private void validateCompteModel(CompteCreateModel compteToCreate) throws Exception {
        ModelNotValidException ex = new ModelNotValidException();

        if(compteToCreate == null) {
            ex.getMessages().add("CompteCreateModel : null");
        }

        if(compteToCreate.getDecouvert() < 0) {
            ex.getMessages().add("Le découvert est négatif");
        }
        if(compteToCreate.getClientIds() == null || compteToCreate.getClientIds().size() == 0) {
            ex.getMessages().add("Aucun titulaire(s) indiqué(s)");
        }

        // Verifie si tous les titulaires font parties de la même agence
        Agence agence = this.clientService.getClientById(compteToCreate.getClientIds().get(0)).getAgence();

        for (Integer id : compteToCreate.getClientIds()) {
            Client client = this.clientService.getClientById(id);

            if (!client.getAgence().getCodeAgence().equals(agence.getCodeAgence())) {
                ex.getMessages().add(String.format(NOT_SAME_AGENCE, client.getIdClient()));
            }
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }

}
