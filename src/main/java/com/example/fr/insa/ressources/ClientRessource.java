package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.models.Client;
import com.example.fr.insa.ressources.dto.ClientCreateModel;
import com.example.fr.insa.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientRessource extends CommonResource {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getAllAgence() {
        return this.clientService.getAllClient();
    }

    @GetMapping("{id}")
    public Client getClient(@PathVariable("id") int id) throws Exception {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody ClientCreateModel clientToCreate) throws FonctionnalProcessException {
        return this.clientService.saveClient(clientToCreate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
