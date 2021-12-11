package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteRepository extends JpaRepository<Carte, String> {

    public Carte findByNumeroCarte(String numeroCarte);
}
