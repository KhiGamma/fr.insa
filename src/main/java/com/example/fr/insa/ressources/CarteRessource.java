package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.models.Carte;
import com.example.fr.insa.ressources.dto.CarteCreateModel;
import com.example.fr.insa.services.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartes")
public class CarteRessource {

    @Autowired
    private CarteService carteService;

    @GetMapping()
    public List<Carte> getAllCarte() {
        return this.carteService.getAllCarte();
    }

    @GetMapping("{id}")
    public Carte getCarte(@PathVariable("id") String id) throws Exception {
        return carteService.getCarteById(id);
    }

    @PostMapping
    public Carte createCarte(@RequestBody CarteCreateModel carteTocreate) throws FonctionnalProcessException {
        return this.carteService.saveCarte(carteTocreate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCarte(@PathVariable("id") String id) {
        this.carteService.deleteCarte(id);
        return ResponseEntity.ok().build();
    }
}
