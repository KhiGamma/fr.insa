package com.example.fr.insa.services;

import com.example.fr.insa.exceptions.BadRequestException;
import com.example.fr.insa.exceptions.FonctionnalProcessException;
import com.example.fr.insa.exceptions.ModelNotValidException;
import com.example.fr.insa.models.Compte;
import com.example.fr.insa.models.Transaction;
import com.example.fr.insa.reposotories.TransactionRepository;
import com.example.fr.insa.ressources.dto.input.TransactionCreateModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
	
	private static final String TRANSACTION_NOT_FOUND = "Transaction non trouvée avec l'id : %s";
    private static final String NOT_ENOUGTH_SOLDE = "Montant trop important : %s";

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CompteService compteService;
    
    public List<Transaction> getAllTransaction() {
        return this.transactionRepository.findAll();
    }

    public Transaction getTransactionById(int id) throws FonctionnalProcessException {
    	Transaction transaction =
                transactionRepository
                        .findById(id)
                        .orElseThrow(() -> new FonctionnalProcessException(String.format(TRANSACTION_NOT_FOUND, id)));
        return transaction;
    }

    @Transactional(rollbackFor = Exception.class)
    public Transaction saveTransaction(TransactionCreateModel transactionToCreate) throws FonctionnalProcessException {

        validateTransactionModel(transactionToCreate);
        
        Compte compteEmetteur = compteService.getCompteById(transactionToCreate.getEmetteur());

        if (compteEmetteur.getSoldeCompte() < transactionToCreate.getMontantTransaction() - compteEmetteur.getDecouvert()) {
            throw new BadRequestException(String.format(NOT_ENOUGTH_SOLDE, transactionToCreate.getMontantTransaction()));
        }

        Transaction a = Transaction.builder()
                .typeTransaction(transactionToCreate.getTypeTransaction())
                .emetteur(compteEmetteur)
                .beneficiaire(transactionToCreate.getBeneficiaire())
                .date(transactionToCreate.getDate())
                .montantTransaction(transactionToCreate.getMontantTransaction())
                .build();

        this.compteService.retirerSoldeCompte(transactionToCreate.getEmetteur(), transactionToCreate.getMontantTransaction());
        this.compteService.ajouterSoldecompte(transactionToCreate.getBeneficiaire(), transactionToCreate.getMontantTransaction());

        return this.transactionRepository.save(a);
    }

    public void deleteTransaction(int id) {
        this.transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionOfcompte(String idCompte) {
        return this.transactionRepository.findByEmetteurNumeroCompte(idCompte);
    }

    private void validateTransactionModel(TransactionCreateModel transactionToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(transactionToCreate == null) {
            ex.getMessages().add("transactionToCreate : null");
        }
        
        if(transactionToCreate.getDate() == null) {
            ex.getMessages().add("Aucune date spécifié");
        }
        if(transactionToCreate.getBeneficiaire() == null || transactionToCreate.getBeneficiaire().isBlank()) {
            ex.getMessages().add("Aucun bénéficiaire");
        }
        if(transactionToCreate.getMontantTransaction() <= 0) {
            ex.getMessages().add("Aucun montant");
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
