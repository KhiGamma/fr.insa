package com.example.fr.insa.services;

import com.example.fr.insa.reposotories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenceService {

    @Autowired
    private AgenceRepository agenceRepository;

}
