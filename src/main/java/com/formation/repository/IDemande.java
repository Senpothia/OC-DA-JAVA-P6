package com.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.model.Commentaire_longueur;
import com.formation.model.Demande;

public interface IDemande extends JpaRepository <Demande, Integer>{

}
