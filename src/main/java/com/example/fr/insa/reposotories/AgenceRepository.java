package com.example.fr.insa.reposotories;

import com.example.fr.insa.models.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer> {
}
