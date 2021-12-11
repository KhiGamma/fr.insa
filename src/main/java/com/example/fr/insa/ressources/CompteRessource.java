package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.models.Compte;
import com.example.fr.insa.models.Transaction;
import com.example.fr.insa.ressources.dto.CompteCreateModel;
import com.example.fr.insa.services.CompteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comptes")
public class CompteRessource {

    @Autowired
    private CompteService compteService;
    
    @GetMapping()
    public List<Compte> getAllAgence() {
        return this.compteService.getAllCompte();
    }

    @GetMapping("{id}")
    public Compte getCompte(@PathVariable("id") int id) throws Exception {
        return compteService.getCompteById(id);
    }

    @PostMapping
    public Compte createCompte(@RequestBody CompteCreateModel compteToCreate) throws FonctionnalProcessException {
        return this.compteService.saveCompte(compteToCreate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCompte(@PathVariable("id") int id) {
        this.compteService.deleteCompte(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/transactions")
    public List<Transaction> getTransactionOfCompte(@PathVariable("id") int id) throws FonctionnalProcessException {
        return this.compteService.getTransactionOfCompte(id);
    }
}
