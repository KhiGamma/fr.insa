package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, String> {
    public Agence findByCodeAgence(String codeAgence);

    public List<Agence> findAgenceByNomAgenceContainingOrAdresseContainingOrVilleContaining(String nomAgence, String adresse, String ville);
}
