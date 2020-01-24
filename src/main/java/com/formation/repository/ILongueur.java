package com.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.model.Commentaire_longueur;
import com.formation.model.Longueur;

public interface ILongueur extends JpaRepository <Longueur, Integer> {

}
