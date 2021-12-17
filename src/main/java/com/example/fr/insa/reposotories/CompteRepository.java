package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

    @Query("SELECT COUNT(c) FROM Carte c WHERE c.compte.numeroCompte = :compte_id")
    public int nombreDeCarte(@Param("compte_id") String compteId);

    public Compte findByNumeroCompte(String numeroCompte);

    public Optional<Compte> findByIban(String iban);
}
