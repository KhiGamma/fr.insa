package com.example.fr.insa.services;

import com.example.fr.insa.reposotories.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteService {

    @Autowired
    private CarteRepository carteRepository;

}
