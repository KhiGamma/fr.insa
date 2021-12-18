package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, String> {
    public Agence findByCodeAgence(String codeAgence);

    public Optional<Agence> findAgenceByNomAgenceOrAdresse(String nomAgence, String Adresse);
}
