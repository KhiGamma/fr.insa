package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findByEmetteurId(int id);
}
