package com.example.fr.insa.ressources;

import com.example.fr.insa.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clients")
public class ClientRessource {

    @Autowired
    private ClientService clientService;

}
