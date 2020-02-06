package com.formation.escalade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.formation.escalade.model.Longueur;
import com.formation.escalade.model.Voie;

public interface ILongueur extends JpaRepository <Longueur, Integer> {

	List<Longueur> findByVoie(Voie s);

}
