package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Agence;
import com.example.fr.insa.models.Client;
import com.example.fr.insa.reposotories.ClientRepository;
import com.example.fr.insa.ressources.dto.ClientCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AgenceService agenceService;

    public List<Client> getAllClient() {
        return this.clientRepository.findAll();
    }

    public Client getClientById(int id) throws Exception {
        return this.clientRepository.findById(id).orElseThrow(Exception::new);
    }

    public Client saveClient(ClientCreateModel clientToCreate) throws FonctionnalProcessException {

        validateClientModel(clientToCreate);

        Agence agence = agenceService.getAgenceById(clientToCreate.getAgenceId());

        Client c = Client.builder()
                .nom(clientToCreate.getNom())
                .prenom(clientToCreate.getPrenom())
                .telephone(clientToCreate.getTelephone())
                .adresse(clientToCreate.getAdresse())
                .codePostal(clientToCreate.getCodePostal())
                .ville(clientToCreate.getVille())
                .comptes(new ArrayList<>())
                .agence(agence)
                .build();

        return this.clientRepository.save(c);
    }

    public void deleteClient(int id) {
        this.clientRepository.deleteById(id);
    }

    private void validateClientModel(ClientCreateModel clientToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(clientToCreate == null) {
            ex.getMessages().add("ClientCreateModel : null");
        }

        if(clientToCreate.getNom() == null || clientToCreate.getNom().isBlank()) {
            ex.getMessages().add("nom du client est vide");
        }
        if(clientToCreate.getPrenom() == null || clientToCreate.getPrenom().isBlank()) {
            ex.getMessages().add("prenom du client est vide");
        }
        if(clientToCreate.getTelephone() == null || clientToCreate.getTelephone().isBlank()) {
            ex.getMessages().add("telephone du client est vide");
        }
        if(clientToCreate.getAdresse() == null || clientToCreate.getAdresse().isBlank()) {
            ex.getMessages().add("adresse du client est vide");
        }
        if(clientToCreate.getCodePostal() == null || clientToCreate.getCodePostal().isBlank()) {
            ex.getMessages().add("code postal du client est vide");
        }
        if(clientToCreate.getVille() == null || clientToCreate.getVille().isBlank()) {
            ex.getMessages().add("ville du client est vide");
        }
        if(clientToCreate.getAge() < 0) {
            ex.getMessages().add("age du client est inferieur à 0");
        }

        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
