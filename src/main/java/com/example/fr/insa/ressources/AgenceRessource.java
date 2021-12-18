package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.models.Agence;
import com.example.fr.insa.ressources.dto.input.AgenceCreateModel;
import com.example.fr.insa.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agences")
public class AgenceRessource extends CommonResource {

    @Autowired
    public AgenceService agenceService;

    @GetMapping("all")
    public List<Agence> getAllAgence() {
        return this.agenceService.getAllAgence();
    }

    @GetMapping()
    public Agence getAgenceByNameOrAdresse(
            @RequestParam(
                name = "nomAgence",
                required = false,
                defaultValue = "") String nomAgence,
            @RequestParam(
                name = "adresse",
                required = false,
                defaultValue = "") String adresse) throws FonctionnalProcessException {
        return agenceService.getAgenceByNomAgenceOrAdresse(nomAgence, adresse);
    }

    @GetMapping("{id}")
    public Agence getAgence(@PathVariable("id") String codeAgence) throws Exception {
        return agenceService.getAgenceById(codeAgence);
    }

    @PostMapping
    public Agence createAgence(@RequestBody AgenceCreateModel agenceToCreate) throws FonctionnalProcessException {
        return this.agenceService.saveAgence(agenceToCreate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAgence(@PathVariable("id") String id) {
        this.agenceService.deleteAgence(id);
        return ResponseEntity.ok().build();
    }
}
