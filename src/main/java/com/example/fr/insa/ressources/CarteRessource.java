package com.example.fr.insa.ressources;

import com.example.fr.insa.services.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cartes")
public class CarteRessource {

    @Autowired
    private CarteService carteService;

}
