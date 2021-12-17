package com.example.fr.insa.ressources;

import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.models.Transaction;
import com.example.fr.insa.ressources.dto.input.TransactionCreateModel;
import com.example.fr.insa.services.TransactionService;

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
@RequestMapping("transactions")
public class TransactionRessource extends CommonResource {

    @Autowired
    private TransactionService transactionService;
    
    @GetMapping()
    public List<Transaction> getAllTransaction() {
        return this.transactionService.getAllTransaction();
    }

    @GetMapping("{id}")
    public Transaction getTransaction(@PathVariable("id") int id) throws Exception {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionCreateModel transactionToCreate) throws FonctionnalProcessException {
        return this.transactionService.saveTransaction(transactionToCreate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") int id) {
        this.transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}
