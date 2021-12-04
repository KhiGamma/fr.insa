package com.example.fr.insa.ressources;

import com.example.fr.insa.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agences")
public class AgenceRessource {

    @Autowired
    private AgenceService agenceService;

}
