package com.example.fr.insa.ressources;

import com.example.fr.insa.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionRessource {

    @Autowired
    private TransactionService transactionService;

}
