package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Agence;
import com.example.fr.insa.ressources.dto.AgenceCreateModel;
import com.example.fr.insa.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agences")
public class AgenceRessource {

    @Autowired
    private AgenceService agenceService;

    @GetMapping()
    public List<Agence> getAllAgence() {
        return this.agenceService.getAllAgence();
    }

    @PostMapping
    public Agence createAgence(@RequestBody AgenceCreateModel agenceToCreate) {
        return this.agenceService.saveAgence(agenceToCreate);
    }


}
