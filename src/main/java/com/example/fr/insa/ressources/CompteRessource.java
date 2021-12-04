package com.example.fr.insa.ressources;

import com.example.fr.insa.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comptes")
public class CompteRessource {

    @Autowired
    private CompteService compteService;

}
