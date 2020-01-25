package com.formation.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Commentaire_longueur;
import com.formation.escalade.model.Demande;

public interface IDemande extends JpaRepository <Demande, Integer>{

}