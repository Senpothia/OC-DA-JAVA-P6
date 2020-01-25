package com.formation.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.escalade.model.Commentaire_longueur;
import com.formation.escalade.model.Longueur;

public interface ILongueur extends JpaRepository <Longueur, Integer> {

}
